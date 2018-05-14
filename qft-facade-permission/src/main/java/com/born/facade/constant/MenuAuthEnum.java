package com.born.facade.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * 
* @ClassName: MenuAuthEnum 
* @Description: 菜单权限枚举
* @author lijie 
* @date 2018年5月4日 下午12:57:59 
*
 */
@Getter
@AllArgsConstructor
public enum MenuAuthEnum {
	
	DELETE((byte)1,"删除"),
	NOT_DELETE((byte)0,"未删除"),
	
	AVAILABLE((byte)1,"可用"),
	UNAVAILABLE((byte)0,"不可用"),
	
	CHECK((byte)1,"选中"),
	NOT_CHECK((byte)0,"未选中"),
	
	POSITION_AUTH((byte)1,"职位权限"),
	PERSION_AUTH((byte)2,"个人权限"),
	
	PC_MENU_AUTH((byte)1,"pc菜单/权限"),
	APP_MENU_AUTH((byte)2,"app菜单/权限")
	
	;
	private Byte status;
	
	private String desc;
}
