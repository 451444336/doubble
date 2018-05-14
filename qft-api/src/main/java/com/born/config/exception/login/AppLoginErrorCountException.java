package com.born.config.exception.login;

import com.born.config.exception.BaseAuthenticationException;

/**
 * App 登录错误次数超限异常类
 * 
 * @author zys
 *
 */
public class AppLoginErrorCountException extends BaseAuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AppLoginErrorCountException() {
		super();
	}
	public AppLoginErrorCountException(String message) {
		super(message);
	}

}
