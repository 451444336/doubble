package com.born.facade.vo.permission;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 
* @ClassName: MenuPermissionVO 
* @Description: 菜单权限 
* @author lijie 
* @date 2018年5月10日 下午2:09:46 
*
 */
@Data
public class MenuPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9191042445820493265L;
	/**
	 * 菜单ID
	 */
	private Long menuId;
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 是否勾选：0、否 1、是
	 */
	private Byte isCheck = 0;
	/**
	 * 菜单权限对应的数据
	 */
	private List<PermissionVO> permissions;
}
