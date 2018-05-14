package com.born.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "登录传输实体")
public class LoginInfo {

	/**
	 * 加密账号
	 */
	@ApiModelProperty("账号(可以不用加密)")
	private String account;
	
	/**
	 * 加密密码
	 */
	@ApiModelProperty("加密密码")
	private String password;
	
	/**
	 * 生成验签
	 * 规则: MD5(明文账号 + 明文密码)
	 */
	@ApiModelProperty("生成验签规则: MD5(account + password)")
	private String  sign;
}
