package com.born.config.shiro;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Reference;
import com.born.config.auth.token.Token;
import com.born.config.auth.token.TokenManager;
import com.born.config.exception.login.AppLoginErrorCountException;
import com.born.config.exception.login.AppLoginPassErrorException;
import com.born.config.exception.login.AppLoginUnifyException;
import com.born.core.rediscache.ICacheService;
import com.born.dto.ResultInfo;
import com.born.dto.UserInfoVO;
import com.born.facade.service.ICompanyRoleAuthService;
import com.born.facade.service.ICompanyStaffService;
import com.born.facade.service.ISysUserService;
import com.born.facade.vo.UserApiVO;
import com.born.facade.vo.appauth.UserRoleAuthVO;
import com.born.facade.vo.staff.StaffVO;
import com.born.util.AppUtil;
import com.born.util.String.StringUtil;
import com.born.util.constants.AppRedisKeyConstants;
import com.born.util.encrypt.security.SecurityUtil;
import com.born.util.result.RespCode;
import com.born.util.result.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * 
* @ClassName: MyShiroRealm 
* @Description: 自定义Shiro认证
* @author 张永胜 
* @date 2018年5月9日 下午3:45:03 
* @version 1.0
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

	/**
	 * 用户账户服务
	 */
	@Reference(version = "1.0.0")
	private ISysUserService iSysUserService;

	/**
	 * 用户基本信息服务
	 */
	@Reference(version = "1.0.0")
	private ICompanyStaffService iStaffService;

	/**
	 * 角色权限服务
	 */
	@Reference(version = "1.0.0")
	private ICompanyRoleAuthService iCompanyRoleAuthService;

	/**
	 * 缓存服务
	 */
	@Autowired
	private ICacheService<String, Object> iCacheService;

	/**
	 * Token管理
	 */
	@Autowired
	private TokenManager tokenManager;

	/**
	 * 授权验证
	 * 
	 * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
		// System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
		// SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
		// for (SysRole role : userInfo.getRoleList()) {
		// authorizationInfo.addRole(role.getRole());
		// for (SysPermission p : role.getPermissions()) {
		// authorizationInfo.addStringPermission("sys.base.user.update");
		// }
		// }
		// authorizationInfo.addRole("admin");
		return null;
	}

	/**
	 * 登录验证
	 * 
	 * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {

		String account = (String) auth.getPrincipal();
		Result res = iSysUserService.getUser(account);

		if (!RespCode.Code.FAIL.getCode().equals(res.getCode())) {
			
			if( res.getData() == null) {
				throw new AppLoginUnifyException("获取账户信息数据为空");
			}
			
			UserApiVO user = (UserApiVO) res.getData();
			
			/** 检查用户登录错误次数 */
			Integer eCount = checkLoginCount(user);

			/** 检查用户状态 */
			checkUserStatus(user);

			/** 从Shiro中获取密码 */
			UsernamePasswordToken upt = (UsernamePasswordToken) auth;
			StringBuilder sb = new StringBuilder(128);
			for (int i = 0; i < upt.getPassword().length; i++) {
				sb.append(upt.getPassword()[i]);
			}

			/** MD5校验密码 */
			if (user.getPassword().equals(SecurityUtil.encryptPassword(account, sb.toString()))) {

				/** 保存用户信息和缓存用户基本信息 */
				ResultInfo resultInfo = busProcessing(user, null);

				AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(resultInfo, sb.toString(),
						getName());

				log.info("login success is [{}]", account);
				return authenticationInfo;
			}

			/**
			 * 重新设置密码输入的错误次数
			 */
			eCount++;
			log.warn("User [{}] Password Is Error Count: {}", account, eCount);
			iCacheService.set(StringUtil.appendRedisKey(AppRedisKeyConstants.LOGIN_ERROR_COUNT, user.getId()), eCount, 1L, TimeUnit.DAYS);
			throw new AppLoginPassErrorException();
		} else {

			log.warn("No User: {}", account);
			throw new UnknownAccountException();
		}

	}

	/**
	 * 检查登录错误次数
	 * 
	 * @param user
	 */
	private Integer checkLoginCount(UserApiVO user) throws AuthenticationException {

		/** 获取缓存中登录错误次数 */
		Integer eCount = 0;
		Object obj = iCacheService.get(StringUtil.appendRedisKey(AppRedisKeyConstants.LOGIN_ERROR_COUNT, user.getId()));

		if (obj != null) {

			eCount = (Integer) obj;

			if (eCount >= 30) {
				log.warn("User [{}] Login Error Count is {}", user.getAccount(), eCount);
				throw new AppLoginErrorCountException();
			}

		}
		return eCount;
	}

	/**
	 * 检查用户状态
	 * 
	 * @param user
	 */
	private void checkUserStatus(UserApiVO user) {

		/** 状态判读 */
		switch (user.getStatus()) {
		case 2:// 注销
			log.warn("User [{}] Disabled", user.getAccount());
			throw new DisabledAccountException();
		case 3:// 锁定
			log.warn("User [{}] Lock", user.getAccount());
			throw new LockedAccountException();
		default:
			break;
		}

		/**
		 * 0 标识关闭,不能APP登录
		 */
		if (user.getIsEnableApp() == 0) {
			log.warn("User [{}] Close App Login", user.getAccount());
			throw new LockedAccountException();
		}

	}

	/**
	 * 保存用户登录信息 缓存基本信息
	 * 
	 * @param user
	 *            用户账户
	 * @param host
	 */
	@SuppressWarnings("unchecked")
	private ResultInfo busProcessing(Object user, String host) throws AuthenticationException {

		try {
			/**
			 * 保存当前登录的用户
			 */
			AppUtil.saveCurrentUser(iCacheService, user);
			UserApiVO u = (UserApiVO) user;
			String userId = String.valueOf(u.getId());
			Result resultStaff = iStaffService.getCompanyStaff(userId);
			Result resultAuth = iCompanyRoleAuthService.findRoleAuthList(userId);

			/**
			 * 封装给APP端必要的信息
			 */
			ResultInfo info = new ResultInfo();

			/**
			 * 生成Token
			 */
			Token token = tokenManager.createToken(userId);
			info.setToken(token);
			
			/**
			 * 设置用户必要信息
			 */
			if (!RespCode.Code.FAIL.getCode().equals(resultStaff.getCode())) {
				if (resultStaff.getData() != null) {
					StaffVO staff = (StaffVO) resultStaff.getData();
					UserInfoVO infoVo = new UserInfoVO();
					infoVo.setUserId(u.getId());
					infoVo.setRealName(staff.getRealname());
					infoVo.setPhone(staff.getPhone());
					info.setUserInfo(infoVo);
					/**
					 * 缓存用户基本信息
					 */
					iCacheService.set(StringUtil.appendRedisKey(AppRedisKeyConstants.USER_INFO, u.getId()), resultStaff.getData());
				}
			}

			/*
			 * 转换权限信息
			 */
			if (!RespCode.Code.FAIL.getCode().equals(resultAuth.getCode())) {
				if (resultAuth.getData() != null) {
					List<UserRoleAuthVO> roleAuthList = (List<UserRoleAuthVO>) resultAuth.getData();
					info.setRoleAuthList(roleAuthList);
					/**
					 * 缓存用户权限信息
					 */
					iCacheService.set(StringUtil.appendRedisKey(AppRedisKeyConstants.USER_PERMISSION_INFO, u.getId()), roleAuthList);
				}
			}
			/**
			 * 登录成功，移除错误次数
			 */
			iCacheService.remove(StringUtil.appendRedisKey(AppRedisKeyConstants.LOGIN_ERROR_COUNT, u.getId()));

			return info;
		} catch (Exception e) {
			throw new AppLoginUnifyException("处理业务异常", e);
		}

	}

}
