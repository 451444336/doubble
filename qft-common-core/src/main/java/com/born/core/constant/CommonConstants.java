package com.born.core.constant;

/**
 * 
 * @ClassName: CommonConstants
 * @Description: 常量
 * @author lijie
 * @date 2018年5月2日 上午10:48:48
 *
 */
public interface CommonConstants {

	/** 私钥Key */
	String PRIVATE_KEY = "rsa.privatekey";
	/** 公钥Key */
	String PUBLIC_KEY = "rsa.publickey";
	/** 签名公钥Key */
	String SIGN_PRIVATE_KEY = "rsa.sign.privatekey";
	/** 签名公钥Key */
	String SIGN_PUBLIC_KEY = "rsa.sign.publickey";

	/**
	 * 后台用户登录session
	 */
	String LOGIN_BACK_USER_SESSION = "login_back_session";

	/**
	 * 系统用户ID
	 */
	Long SYSTEM_USER = 0L;
	/**
	 * 权限标记
	 */
	String AUTH_FLAG = "3";
}
