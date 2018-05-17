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
public class UserRoleVO implements Serializable {

    private static final long serialVersionUID = 1271305496020041687L;

    /**
     * 角色id
     */
    private Long id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色编码
     */
    private String roleCode;
    /**
     * 是否能权限编辑：0、不能编辑 1、可以编辑
     */
    private Byte isAuthEdit;
}
