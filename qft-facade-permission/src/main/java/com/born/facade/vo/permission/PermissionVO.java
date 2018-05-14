package com.born.facade.vo.permission;

import com.born.core.entity.EntityClone;

import lombok.Data;
/**
 * 
* @ClassName: PersonalPermissionVO 
* @Description: 权限数据
* @author lijie 
* @date 2018年5月9日 下午5:02:41 
*
 */
@Data
public class PermissionVO extends EntityClone<PermissionVO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 菜单ID
	 */
	private Long menuId;
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 权限ID
	 */
	private Long authorityId;
	/**
	 * 权限名称
	 */
	private String authorityName;
	/**
	 * 权限地址
	 */
	private String authorityUrl;
	/**
	 * 权限操作类型：1、查看 2、修改删除 3、操作 4、特殊
	 */
	private Byte operType;
	/**
	 * 是否勾选：0、否 1、是
	 */
	private Byte isCheck = 0;
}
