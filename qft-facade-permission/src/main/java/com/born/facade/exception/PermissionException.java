package com.born.facade.exception;

import com.born.core.exception.BizException;
import com.born.util.result.RespCode;
/**
 * 
* @ClassName: PermissionException 
* @Description: 权限异常类 
* @author lijie 
* @date 2018年4月27日 上午9:30:18 
*
 */
public class PermissionException extends BizException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public PermissionException(RespCode exceptionEnum) {
        super(exceptionEnum.getMsg(),exceptionEnum.getCode());
    }

    public PermissionException() {
    	super();
    }

    public PermissionException(String message) {
        super(message);
    }
}
