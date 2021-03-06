package com.born.facade.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 
* @ClassName: SessionUserVO 
* @Description: 登录后的用户表 
* @author lijie 
* @date 2018年5月2日 上午10:41:09 
*
 */
@Data
public class UserInfoVO implements Serializable {
	
    private static final long serialVersionUID = 6829940553030159312L;

    /**
     * 用户登录表id
     */
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 账号
     */
    private String account;
    /**
     * 公司跳转url
     */
    private String corUrl;
    /**
     * 公司ID
     */
    private Long companyId;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 登陆账户对应的角色
     */
    private List<UserRoleVO> roles;

}
