package com.born.facade.exception;

import com.born.core.exception.BizException;
import com.born.util.result.RespCode;

/**
 * 字典服务异常
 * 
 * @ClassName: ConfigSetException
 * @Description: 字典服务异常
 * @author 张永胜
 * @date 2018年5月30日 下午5:00:50
 * @version 1.0
 */
public class DicException extends BizException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DicException(RespCode exceptionEnum) {
		super(exceptionEnum.getMsg(), exceptionEnum.getCode());
	}

	public DicException() {
		super();
	}

	public DicException(String message) {
		super(message);
	}
}
