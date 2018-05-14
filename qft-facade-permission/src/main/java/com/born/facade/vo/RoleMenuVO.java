package com.born.facade.vo;


import lombok.Data;

import java.io.Serializable;

/**
 * 
* @ClassName: SessionRoleVO 
* @Description: 登陆角色vo实体 
* @author lijie 
* @date 2018年5月2日 上午10:39:57 
*
 */
@Data
public class RoleMenuVO implements Serializable {

	private static final long serialVersionUID = 355788053013637962L;
	/**
     * 角色ID
     */
    private Long roleId;
    /**
     * 菜单ID
     */
    private String menuId;

}
