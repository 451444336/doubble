package com.born.facade.dto.user;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * @ClassName: UserPassDTO
 * @Description: 修改用户密码时用的实体对象
 * @author 张永胜
 * @date 2018年5月9日 上午11:08:41
 * @version 1.0
 */
@Data
public class UserPassDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private String id;

	/**
	 * 加密后的密文（暂时没加密）
	 */
	private String password;

	/**
	 * 用户账号
	 */
	private String account;
}
