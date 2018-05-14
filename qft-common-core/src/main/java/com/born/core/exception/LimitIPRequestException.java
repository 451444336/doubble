package com.born.core.exception;

/**
 * 
 * @ClassName: LimitIPRequestException
 * @Description: 限制请求访问异常类
 * @author 张永胜
 * @date 2018年5月10日 上午9:55:21
 * @version 1.0
 */
public class LimitIPRequestException extends BizException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LimitIPRequestException() {
	}

	public LimitIPRequestException(String message) {
		super(message);
	}

	public LimitIPRequestException(String message, Throwable e) {
		super(message, e);
	}

}
