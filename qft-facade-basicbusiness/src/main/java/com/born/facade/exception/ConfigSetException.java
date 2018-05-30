package com.born.facade.exception;

import com.born.core.exception.BizException;
import com.born.util.result.RespCode;

/**
 * 通用设置异常
* @ClassName: ConfigSetException 
* @Description: 通用设置异常
* @author 张永胜 
* @date 2018年5月30日 下午5:00:50 
* @version 1.0
 */
public class ConfigSetException extends BizException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConfigSetException(RespCode exceptionEnum) {
		super(exceptionEnum.getMsg(), exceptionEnum.getCode());
	}

	public ConfigSetException() {
		super();
	}

	public ConfigSetException(String message) {
		super(message);
	}
}
