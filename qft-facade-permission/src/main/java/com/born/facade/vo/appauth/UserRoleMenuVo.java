package com.born.facade.vo.appauth;

import java.util.Set;

import lombok.Data;

/**
 * 角色菜单VO
 * 
 * @author zys
 *
 */
@Data
public class UserRoleMenuVo {

	/**
	 * ID
	 */
	private String id;

	/**
	 * 菜单名称
	 */
	private String menuName;

	/**
	 * 上级菜单ID
	 */
	private String parentId;

	/**
	 * 菜单地址
	 */
	private String menuUrl;

	/**
	 * 菜单图标
	 */
	private String icon;

	/**
	 * 子菜单
	 */
	private Set<UserRoleMenuVo> childs;

	/**
	 * 菜单权限列表
	 */
	//private Set<UserMenuAuthVo> menuAuthList;

}
