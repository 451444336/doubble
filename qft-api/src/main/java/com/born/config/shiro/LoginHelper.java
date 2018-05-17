package com.born.config.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.born.config.auth.token.Token;
import com.born.config.exception.login.AppLoginErrorCountException;
import com.born.config.exception.login.AppLoginPassErrorException;
import com.born.config.exception.login.AppLoginUnifyException;
import com.born.core.constant.CommonConstants;
import com.born.core.constant.PropertiesConstants;
import com.born.core.rediscache.ICacheService;
import com.born.dto.LoginInfo;
import com.born.dto.RegisterInfo;
import com.born.dto.ResultInfo;
import com.born.facade.entity.User;
import com.born.facade.service.ISysUserService;
import com.born.util.AppUtil;
import com.born.util.constants.AppConstants;
import com.born.util.encrypt.security.SecurityUtil;
import com.born.util.json.JsonResult;
import com.born.util.json.ResultCode;
import com.born.util.json.ResultEntity;
import com.born.util.result.RespCode;
import com.born.util.result.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * Shiro 登录辅助类
 * 
 * @author zys
 *
 */
@Slf4j
public class LoginHelper {

	/**
	 * 用户登录
	 * 
	 * @param loginInfo
	 *            传入的登录参数
	 * @param ip
	 * @return
	 */
	public static final ResultEntity<Object> login(LoginInfo loginInfo, String ip) {

		if (loginInfo == null) {
			return JsonResult.info(ResultCode.INVALID_REQUEST);
		}

		String account = loginInfo.getAccount();
		String password = SecurityUtil.decryptRSAPrivate(loginInfo.getPassword(),
				PropertiesConstants.PROPERTIES_MAP.get(CommonConstants.PRIVATE_KEY));

		/**
		 * SignRSA 验签 防止唯一接口劫持篡改
		 */
		if (!SecurityUtil.verifyRSA(SecurityUtil.encryptMd5Hex(account + password),
				PropertiesConstants.PROPERTIES_MAP.get(CommonConstants.SIGN_PUBLIC_KEY), loginInfo.getSign())) {
			return JsonResult.info(ResultCode.INCORRECT_SIGNATURE);
		}

		UsernamePasswordToken token = new UsernamePasswordToken(account, password, ip);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);

			// 如果为true 登录成功
			if (subject.isAuthenticated()) {
				/**
				 * 获取封装返回给APP的信息对象
				 */
				ResultInfo result = (ResultInfo) subject.getPrincipal();

				return JsonResult.info(ResultCode.SUCCESS, result);
			}
		} catch (UnknownAccountException e) {
			log.warn("没有此账户 [{}]", account);
			return JsonResult.info(ResultCode.UNKNOWN_USERNAME);
		} catch (AppLoginPassErrorException e) {
			log.warn("密码错误  [{}]", account);
			return JsonResult.info(ResultCode.FAIL, "登录密码错误");
		} catch (LockedAccountException e) {
			log.warn("账户已锁定 [{}]", account);
			return JsonResult.info(ResultCode.FAIL, "账户已锁定");
		} catch (DisabledAccountException e) {
			log.warn("您的账号处于关闭状态，请联系管理员开通  [{}]", account);
			return JsonResult.info(ResultCode.FAIL, "您的账号处于关闭状态，请联系管理员开通");
		} catch (AppLoginErrorCountException e) {
			log.warn("密码输入错误超过30次,已经被关闭 [{}]", account);
			return JsonResult.info(ResultCode.FAIL, "密码输入错误超过30次,已经被关闭");
		} catch (AppLoginUnifyException e) {
			log.error("[{}] {}", account, e.getMessage(), e);
			return JsonResult.info(ResultCode.FAIL, "处理异常，请稍后重试");
		} catch (Exception e) {
			log.warn("登录异常  [{}]", account);
			return JsonResult.info(ResultCode.INTERNAL_SERVER_ERROR, "登录异常");
		}
		log.warn("登录异常  [{}]", account);
		return JsonResult.info(ResultCode.INTERNAL_SERVER_ERROR, "登录异常");
	}

	/**
	 * 登出接口
	 * 
	 * @param Token
	 *            传入的Token信息
	 * @param ip
	 * @return
	 */
	public static final ResultEntity<Object> logout(Token token, String ip,
			ICacheService<String, Object> iCacheService) {
		try {
			String userId = SecurityUtil.decryptDes(token.getUserId());
			iCacheService.remove(AppConstants.TOKEN_KEY + ":" + userId);
			iCacheService.remove(AppConstants.USER_INFO + ":" + userId);
			AppUtil.removeCurrentUser(iCacheService, AppConstants.CURRENT_USER + ":" + userId);
			return JsonResult.info(ResultCode.SUCCESS);
		} catch (Exception e) {
			log.error("登出异常", e);
			return JsonResult.info(ResultCode.FAIL);
		}
	}

	/**
	 * 注册
	 * 
	 * @param loginInfo
	 * @param ip
	 * @return
	 */
	public static final ResultEntity<Object> register(RegisterInfo registerInfo, String ip,
			ISysUserService iSysUserService) {

		if (registerInfo == null) {
			return JsonResult.info(ResultCode.INVALID_REQUEST);
		}

		try {

			String account = registerInfo.getAccount();
			String password = SecurityUtil.decryptRSAPrivate(registerInfo.getPassword(),
					PropertiesConstants.PROPERTIES_MAP.get(CommonConstants.PRIVATE_KEY));
			String deviceNumber = registerInfo.getDeviceNumber();
			String sourceType = registerInfo.getSourceType();

			/**
			 * SignRSA 验签 防止唯一接口劫持篡改
			 */
			if (!SecurityUtil.verifyRSA(SecurityUtil.encryptMd5Hex(account + deviceNumber + password + sourceType),
					PropertiesConstants.PROPERTIES_MAP.get(CommonConstants.SIGN_PUBLIC_KEY), registerInfo.getSign())) {
				return JsonResult.info(ResultCode.INCORRECT_SIGNATURE);
			}

			User user = new User();
			user.setAccount(account);
			user.setPassword(SecurityUtil.encryptPassword(account, password));
			user.setDeviceNumber(deviceNumber);
			user.setSourceType(sourceType);

			/**
			 * User 其他字段信息在服务里面设置了
			 */
			Result result = iSysUserService.saveUser(user);

			if (result == null) {
				return JsonResult.info(ResultCode.UNKNOWN_ERROR);
			}

			if (RespCode.Code.FAIL.getCode().equals(result.getCode())) {
				return JsonResult.info(ResultCode.FAIL);
			}

			return JsonResult.info(ResultCode.SUCCESS, result.getData());

		} catch (Exception e) {
			log.error("注册时发生了异常", e);
			return JsonResult.info(ResultCode.INTERNAL_SERVER_ERROR);
		}

	}
}
