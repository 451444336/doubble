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

}
