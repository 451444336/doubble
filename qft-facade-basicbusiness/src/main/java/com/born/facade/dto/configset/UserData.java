package com.born.facade.dto.configset;

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

	public UserData(String type, Long userId, Long companyId) {
		this.type = type;
		this.userId = userId;
		this.companyId = companyId;
	}

	private String type;
	private Long userId;
	private Long companyId;
}
