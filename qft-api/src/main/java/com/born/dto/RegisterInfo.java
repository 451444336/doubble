package com.born.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "注册传输实体")
public class RegisterInfo {

	/**
	 * 加密账号
	 */
	@ApiModelProperty("账号")
	private String account;
	
	/**
	 * 加密密码
	 */
	@ApiModelProperty("加密密码")
	private String password;
	
	/**
	 * 生成验签
	 * 规则: MD5(account + deviceNumber + password + sourceType)
	 */
	@ApiModelProperty("生成验签规则: MD5(account + deviceNumber + password + sourceType)")
	private String  sign;
	
	/**
	 * 设备号
	 */
	@ApiModelProperty("设备号")
	private String deviceNumber;
	
	/**
	 * 注册来源
	 */
	@ApiModelProperty("注册来源")
	private String sourceType;
}
