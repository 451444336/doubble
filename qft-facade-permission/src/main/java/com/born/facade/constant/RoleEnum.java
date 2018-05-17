package com.born.facade.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * 
* @ClassName: RoleEnum 
* @Description: 角色相关枚举 
* @author lijie 
* @date 2018年5月16日 下午6:40:09 
*
 */
@Getter
@AllArgsConstructor
public enum RoleEnum {

	ADMIN(1L,"admin","超级管理员"),
	
	USER(2L,"user","用户");
	
	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 角色编码
	 */
	private String code;
	/**
	 * 角色描述
	 */
	private String desc;
}
