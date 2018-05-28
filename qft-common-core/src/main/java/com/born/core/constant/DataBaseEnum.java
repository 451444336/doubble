package com.born.core.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * @ClassName: DataBaseConstants
 * @Description: 数据库常量
 * @author: lijie
 * @date: 2018年5月27日 下午3:12:25
 */
@Getter
@AllArgsConstructor
public enum DataBaseEnum {
	
	DELETE((byte)1,"删除"),
	
	NOT_DELETE((byte)0,"未删除");
	
	private Byte status;
	
	private String desc;
}
