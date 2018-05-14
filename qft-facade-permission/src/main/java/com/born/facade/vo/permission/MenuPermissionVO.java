package com.born.facade.vo.permission;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 
* @ClassName: MenuPermissionVO 
* @Description: TODO(这里用一句话描述这个类的作用) 
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
	 * 菜单权限对应的数据
	 */
	private List<PermissionVO> permissions;
}
