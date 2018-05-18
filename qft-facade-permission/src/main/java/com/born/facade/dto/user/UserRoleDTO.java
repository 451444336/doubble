package com.born.facade.dto.user;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
 * 
* @ClassName: UserDTO 
* @Description: 用户请求传输DTO 
* @author 明成
* @date 2018年5月2日 上午10:58:40 
*
 */
@Data
public class UserRoleDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 用户id
     */
    private Long id;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private Long createrId;
}
