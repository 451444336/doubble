package com.born.dto;

import lombok.Data;

/**
 * 用户
 * 
 * @author Administrator
 *
 */
@Data
public class UserInfoVO {

	/**
	 * 用户ID
	 */
	private long userId;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 联系方式
	 */
	private String phone;
}
