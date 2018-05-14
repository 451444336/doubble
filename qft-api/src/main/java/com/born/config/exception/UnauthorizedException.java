package com.born.config.exception;

/**
 * 未授权异常
 * 
 * @author zys
 *
 */
public class UnauthorizedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnauthorizedException(String msg) {
		super(msg);
	}

	public UnauthorizedException() {
		super();
	}
}
