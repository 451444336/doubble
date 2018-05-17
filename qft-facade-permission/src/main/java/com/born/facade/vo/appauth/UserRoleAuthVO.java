package com.born.facade.vo.appauth;

import java.util.Set;

import lombok.Data;

/**
 * 角色信息
 * 
 * @author zys
 *
 */
@Data
public class UserRoleAuthVO {

	/**
	 * ID
	 */
	private String id;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 是否能权限编辑
	 */
	private Byte isAuthEdit;

	/**
	 * 角色菜单列表
	 */
	private Set<UserRoleMenuVo> menulist;
}
