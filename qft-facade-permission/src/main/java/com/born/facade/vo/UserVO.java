package com.born.facade.vo;

import java.io.Serializable;

import lombok.Data;
/**
 * 
* @ClassName: UserVO 
* @Description: 用户信息表
* @author lijie 
* @date 2018年5月2日 上午11:27:42 
*
 */
@Data
public class UserVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 368559934242471746L;
	/**
     * 用户id
     */
    private Long id;
    /**
     * 手机号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
}
