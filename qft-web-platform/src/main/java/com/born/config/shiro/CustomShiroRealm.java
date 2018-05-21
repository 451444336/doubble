package com.born.config.shiro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.born.config.shiro.token.ShiroToken;
import com.born.facade.dto.user.LoginDTO;
import com.born.facade.exception.PermissionExceptionEnum;
import com.born.facade.service.IMenuAuthorityService;
import com.born.facade.service.IMenuService;
import com.born.facade.service.IUserService;
import com.born.facade.vo.MenuAuthorityVO;
import com.born.facade.vo.MenuVO;
import com.born.facade.vo.UserAuthVO;
import com.born.facade.vo.UserInfoVO;
import com.born.facade.vo.UserRoleVO;
import com.born.util.result.RespCode;
import com.born.util.result.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * 
* @ClassName: MyShiroRealm 
* @Description: 权限认证 
* @author lijie 
* @date 2018年4月28日 下午4:41:24 
*
 */
@Slf4j
public class CustomShiroRealm extends AuthorizingRealm {

    // 用户接口
    @Autowired
    private IUserService userService;
    // 菜单接口
    @Autowired
    private IMenuService menuService;
    // 菜单权限接口
    @Autowired
    private IMenuAuthorityService menuAuthService;

   /**
    * 
   * @Title: getMenuService 
   * @Description:  获取菜单接口方法，用于配置文件调用菜单接口 
   * @param @return    设定文件 
   * @return IMenuService    返回类型 
   * @author lijie
   * @throws
    */
    public IMenuService getMenuService() {
        return menuService;
    }

    /**
     * 
    * @Title: getMenuAuthorityService 
    * @Description: 获取权限接口方法
    * @param @return    设定文件 
    * @return IMenuAuthorityService    返回类型 
    * @author lijie
    * @throws
     */
    public IMenuAuthorityService getMenuAuthorityService() {
        return menuAuthService;
    }

    /**
     * 权限认证，为当前登录的Subject授予角色和权限
     * 经测试：本例中该方法的调用时机为需授权资源被访问时
     * 经测试：并且每次访问需授权资源时都会执行该方法中的逻辑，这表明本例中默认并未启用AuthorizationCache
     * 经测试：如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（也就是cache时间，在ehcache-shiro.xml中配置），超过这个时间间隔再刷新页面，该方法会被执行
     * 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
     * @param principalCollection
     * @return AuthorizationInfo
     * @author lijie
     */
	@Override
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		log.info(">> ........ 执行Shiro权限认证 ............. >> ");
		UserInfoVO userInfo = (UserInfoVO) SecurityUtils.getSubject().getPrincipal();
		// 权限信息对象info,用来存放用户的所有角色（role）及权限（permission）
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(roleToStrs(userInfo.getRoles()));
		
		List<MenuVO> menus = getMenus(userInfo.getRoles());
		if (CollectionUtils.isNotEmpty(menus)) {
			info.addStringPermissions(menuToStrs(menus));
			// 按钮权限
			// Session按钮集合
			Collection<String> permissions = new HashSet<>();
			// 根据菜单ID 获取菜单操作权限数据
			List<String> auths = getUserAuths(userInfo.getId(), menus);
			if (CollectionUtils.isNotEmpty(auths)) {
				info.addStringPermissions(auths);
				permissions.addAll(auths);
			}
			log.info("该用户总角色数为：{}", info.getRoles().size());
			log.info("该用户总权限数为：{}", info.getStringPermissions().size());
			log.info("权限详细列表如下:");
			for (String s : info.getStringPermissions()) {
				log.info(s);
			}
			//获取当前的Subject把权限按钮放入Session中
            Session session = SecurityUtils.getSubject().getSession();
            for (String s : permissions) {
                session.setAttribute(s, s);
            }
		}
		return info;
	}
	/**
	 * 
	* @Title: menuToStrs  
	* @Description: 菜单值转换 
	* @param: @param menus
	* @param: @return
	* @return List<String>
	* @author lijie
	* @throws
	 */
	private List<String> menuToStrs(List<MenuVO> menus) {
		List<String> result = new ArrayList<>();
		for (MenuVO menu : menus) {
			if (StringUtils.isNotBlank(menu.getMenuUrl())) {
				result.add(menu.getMenuUrl());
			}
		}
		return result;
    }
	/**
	 * 
	* @Title: roleToStrs  
	* @Description: 角色转换 
	* @param: @param roles
	* @param: @return
	* @return Set<String>
	* @author lijie
	* @throws
	 */
	private Set<String> roleToStrs(List<UserRoleVO> roles) {
        Set<String> set = new HashSet<>();
        for (UserRoleVO role : roles) {
            set.add(role.getRoleCode());
        }
        return set;
    }
    /**
     * 
    * @Title: getUserAuths  
    * @Description: 查询操作权限数据 
    * @param: @param userId
    * @param: @param menus
    * @param: @return
    * @return List<MenuAuthorityVO>
    * @author lijie
    * @throws
     */
	@SuppressWarnings("unchecked")
	private List<String> getUserAuths(final Long userId, final List<MenuVO> menus) {
		final List<String> result = new LinkedList<>();
		// 查询用户操作权限
		final Set<Long> authSet = new HashSet<>();
		Result uAuths = menuAuthService.getAuthorityListByUserId(userId);
		if (uAuths.isSuccess()) {
			List<UserAuthVO> uAuthList = (List<UserAuthVO>) uAuths.getData();
			if (CollectionUtils.isNotEmpty(uAuthList)) {
				for (UserAuthVO ua : uAuthList) {
					authSet.add(ua.getAuthorityId());
				}
			}
		}

		final List<Long> menuIds = new ArrayList<>();
		for (MenuVO m : menus) {
			menuIds.add(m.getId());
		}
		// 根据菜单ID 获取菜单操作权限数据
		Result maResult = menuAuthService.getAuthorityListByMenuIds(menuIds);
		if (maResult.isSuccess()) {
			List<MenuAuthorityVO> malist = (List<MenuAuthorityVO>) maResult.getData();
			if (CollectionUtils.isNotEmpty(malist)) {
				for (MenuAuthorityVO ma : malist) {
					if (authSet.contains(ma.getAuthorityId())) {
						result.add(ma.getAuthCode());
					}
				}
			}
		}

		return result;
	}
    /**
     * 
    * @Title: getMenus  
    * @Description: 根据角色ID 查询菜单数据 
    * @param: @param roles
    * @param: @return
    * @return List<MenuVO>
    * @author lijie
    * @throws
     */
	@SuppressWarnings("unchecked")
	private List<MenuVO> getMenus(final List<UserRoleVO> roles) {
		// 菜单权限id
		final List<Long> roleList = new ArrayList<>();
		for (UserRoleVO role : roles) {
			roleList.add(role.getId());
		}
		Result menuResult = menuService.getMenuByRoleIds(roleList);
		if (log.isInfoEnabled()) {
			log.info("根据角色ID查询菜单返回数据={}", JSON.toJSONString(menuResult));
		}
		if (menuResult.isSuccess()) {
			return (List<MenuVO>) menuResult.getData();
		}
		return null;
	}
    /**
     * 
     * @param authenticationToken
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @author lijie
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		// UsernamePasswordToken对象用来存放提交的登录信息
		ShiroToken token = (ShiroToken) authenticationToken;
		if (log.isInfoEnabled()) {
			log.info("验证当前Subject时获取到token为：{}",
					ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
		}
		LoginDTO loginDto = new LoginDTO();
		loginDto.setAccount(token.getUsername());
		loginDto.setIp(token.getIp());
		loginDto.setPassword(token.getPswd());
		Result result = userService.login(loginDto);
		if (!result.isSuccess()) {
			if (RespCode.Code.REQUEST_DATA_ERROR.getCode().equals(result.getCode())
					|| PermissionExceptionEnum.AUTHENTICATION.getCode().equals(result.getCode())) {
				throw new AccountException("帐号或密码不正确");
			} else if (PermissionExceptionEnum.USER_NON_EXISTENT.getCode().equals(result.getCode())) {
				throw new AccountException("帐号不存在");

			} else if (PermissionExceptionEnum.USER_LOGOUT.getCode().equals(result.getCode())) {
				throw new DisabledAccountException("帐号已经禁止登录");
			} else {
				throw new UnknownAccountException("用户登录未知错误");
			}
		}
		UserInfoVO userInfo = result.getData(UserInfoVO.class);
		if (CollectionUtils.isEmpty(userInfo.getRoles())) {
			throw new UnknownAccountException("用户角色信息不存在");
		} else {
			// 若账户存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
			return new SimpleAuthenticationInfo(userInfo, token.getPswd(), getName());
		}
	}
	/**
	 * 
	* @Title: clearAuthz 
	* @Description: 清楚缓存权限 
	* @param     设定文件 
	* @return void    返回类型 
	* @author lijie
	* @throws
	 */
	public void clearAuthz() {
		this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
	}
}