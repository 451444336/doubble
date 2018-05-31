package com.born.core.entity;

import java.io.Serializable;

import lombok.Data;

/***
 * 
 * @ClassName: UserData
 * @Description: 当前登录用户信息
 * @author 张永胜
 * @date 2018年5月30日 下午3:01:01
 * @version 1.0
 */
@Data
public class UserData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserData(Long userId, Long companyId) {
		this.userId = userId;
		this.companyId = companyId;
	}

	public UserData(String type, Long userId, Long companyId) {
		this.type = type;
		this.userId = userId;
		this.companyId = companyId;
	}

	/**
	 * 标识集中、整租、合租
	 */
	private String type;

	/**
	 * 当前登录用户ID
	 */
	private Long userId;

	/**
	 * 公司ID
	 */
	private Long companyId;
}
