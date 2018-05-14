package com.born.config.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * 认证异常基类
 * 
 * @author zys
 *
 */
public abstract class BaseAuthenticationException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public BaseAuthenticationException() {
		super();
	}

	public BaseAuthenticationException(Throwable ex) {
		super(ex);
	}

	public BaseAuthenticationException(String message) {
		super(message);
	}

	public BaseAuthenticationException(String message, Throwable ex) {
		super(message, ex);
	}
}
