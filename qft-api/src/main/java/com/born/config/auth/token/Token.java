package com.born.config.auth.token;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @ClassName: Token
 * @Description:Token的实体类，可以增加字段提高安全性，例如时间戳、url签名
 * @author 张永胜
 * @date 2018年5月4日 下午6:42:07
 * @version 1.0
 */
@Data
@ApiModel(value = "Token实体")
public class Token {

	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
	private String userId;

	/**
	 * 随机生成的token值
	 */
	@ApiModelProperty("token值")
	private String token;

	public Token(String userId, String token) {
		this.userId = userId;
		this.token = token;
	}
}
