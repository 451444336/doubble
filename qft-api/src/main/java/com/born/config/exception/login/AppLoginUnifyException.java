package com.born.config.exception.login;

import com.born.config.exception.BaseAuthenticationException;

/**
 * 登录时通用异常处理类
 * 
 * @author zys
 *
 */
public class AppLoginUnifyException extends BaseAuthenticationException {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public AppLoginUnifyException() {
		super();
	}

	public AppLoginUnifyException(String message) {
		super(message);
	}

	public AppLoginUnifyException(String message, Throwable ex) {
		super(message, ex);
	}

}
