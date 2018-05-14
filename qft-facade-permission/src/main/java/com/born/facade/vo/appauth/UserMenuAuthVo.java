package com.born.facade.vo.appauth;

import lombok.Data;

/**
 * 角色菜单VO
 * 
 * @author zys
 *
 */
@Data
public class UserMenuAuthVo {

	/**
	 * ID
	 */
	private String id;

	/**
	 * 权限名称
	 */
	private String authorityName;

	/**
	 * 权限地址
	 */
	private String authorityUrl;

	/**
	 * 手机URL
	 */
	private String appUrl;

	/**
	 * 权限图标
	 */
	private String icon;
}
