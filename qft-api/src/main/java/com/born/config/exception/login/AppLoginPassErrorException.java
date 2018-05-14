package com.born.config.exception.login;

import com.born.config.exception.BaseAuthenticationException;

/**
 * App 登录错误次数超限异常类
 * 
 * @author zys
 *
 */
public class AppLoginPassErrorException extends BaseAuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AppLoginPassErrorException() {
		super();
	}
	public AppLoginPassErrorException(String message) {
		super(message);
	}

}
