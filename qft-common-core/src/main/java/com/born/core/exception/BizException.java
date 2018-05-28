package com.born.core.exception;

import lombok.Data;

/**
 * 
* @ClassName: BizException  
* @Description: 异常处理基础类
* @author lijie
* @date 2018年4月25日  
*
 */
@Data
public class BizException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2128062111650408907L;

	// 错误消息
	private String msg;
	// 错误代码
	private String code;

	public BizException(String msg, String code, Object... args) {
		super(String.format(msg, args));
		this.msg = msg;
		this.code = code;
	}

	public BizException(String msg, String code) {
		super();
		this.msg = msg;
		this.code = code;
	}

	public BizException() {
		super();
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(String message) {
		super(message);
	}

	public BizException(Throwable cause) {
		super(cause);
	}
}
