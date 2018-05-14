package com.born.core.exception;

/**
 * 
* @ClassName: BizException  
* @Description: 异常处理基础类
* @author lijie
* @date 2018年4月25日  
*
 */
public class BizException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2128062111650408907L;
	
	// 错误消息
	private String msg;
	// 错误代码
	protected String code;
	
	public BizException(String msg, String code, Object...args) {
		super(String.format(msg,args));
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

	/**
	 * 实例化异常
	 * @param message
	 * @param args
	 * @return
	 * @author chang
	 */
	public BizException newInstance(String message, Object...args) {
		return new BizException(message,this.code,args);
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
