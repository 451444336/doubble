package com.born.facade.exception;

import com.born.core.exception.BizException;
import com.born.util.result.RespCode;
/**
 * 
* @ClassName: OrderException 
* @Description: 订单异常 
* @author lijie 
* @date 2018年5月15日 下午12:55:49 
*
 */
public class BasicBusinessException extends BizException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BasicBusinessException(RespCode exceptionEnum) {
		super(exceptionEnum.getMsg(), exceptionEnum.getCode());
	}

	public BasicBusinessException() {
		super();
	}

	public BasicBusinessException(String message) {
		super(message);
	}
}
