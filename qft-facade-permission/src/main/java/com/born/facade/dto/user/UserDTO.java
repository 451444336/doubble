package com.born.facade.dto.user;

import java.io.Serializable;

import lombok.Data;
/**
 * 
* @ClassName: UserDTO 
* @Description: 用户请求传输DTO 
* @author lijie 
* @date 2018年5月2日 上午10:58:40 
*
 */
@Data
public class UserDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 用户id
     */
    private Long id;
    /**
     * 用户账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String msgCode;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 公司url
     */
    private String corUrl;
}
