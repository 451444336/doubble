package com.born.facade.exception.focus.housing;

import com.born.core.exception.BizException;
import com.born.util.result.RespCode;
/**
 * 
* @ClassName: FocusHousingException 
* @Description: 集中整租异常 
* @author 黄伟
* @date 2018年5月25日 下午6:14:53 
*
 */
public class LeaseException extends BizException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LeaseException(RespCode exceptionEnum) {
		super(exceptionEnum.getMsg(), exceptionEnum.getCode());
	}

	public LeaseException() {
		super();
	}

	public LeaseException(String message) {
		super(message);
	}
}
