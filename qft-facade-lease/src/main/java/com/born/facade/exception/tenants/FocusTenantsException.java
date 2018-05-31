package com.born.facade.exception.tenants;

import com.born.core.exception.BizException;
import com.born.util.result.RespCode;

/**
 * 
 * @ClassName: FocusHousingException
 * @Description: 集中租客异常
 * @author 张永胜
 * @date 2018年5月25日 下午6:14:53
 *
 */
public class FocusTenantsException extends BizException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FocusTenantsException(RespCode exceptionEnum) {
		super(exceptionEnum.getMsg(), exceptionEnum.getCode());
	}

	public FocusTenantsException() {
		super();
	}

	public FocusTenantsException(String message) {
		super(message);
	}
}
