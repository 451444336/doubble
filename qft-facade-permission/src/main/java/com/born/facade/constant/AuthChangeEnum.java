package com.born.facade.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * 
* @ClassName: AuthOperEnum 
* @Description:权限相关操作枚举
* @author lijie 
* @date 2018年5月14日 下午2:41:02 
*
 */
@Getter
@AllArgsConstructor
public enum AuthChangeEnum {
	
	ADD((byte) 1, "新增"), 
	
	UPDATE((byte) 2, "删除"), 
	
	DELETE((byte) 3, "修改");
	
	private Byte status;

	private String desc;

}
