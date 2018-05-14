package com.born.facade.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * 
* @ClassName: UserEnum 
* @Description: 用户枚举 
* @author lijie 
* @date 2018年5月7日 下午1:41:46 
*
 */
@Getter
@AllArgsConstructor
public enum UserEnum {

	NORMAL((byte)1,"正常"),
	
	LOGOUT((byte)2,"注销"),
	
	DELETE((byte)1,"删除"),
	
	NOT_DELETE((byte)0,"未删除");
	
	private Byte status;
	
	private String desc;
	
}
