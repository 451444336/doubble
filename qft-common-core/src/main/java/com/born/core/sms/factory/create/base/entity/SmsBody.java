package com.born.core.sms.factory.create.base.entity;

import java.util.Map;

import lombok.Data;

@Data
public class SmsBody {

	/**
	 * 请求方式,每个类都应默认指定
	 */
	private String method;

	/**
	 * 请求URL
	 */
	private String url;

	/**
	 * 请求参数
	 */
	private Map<String, String> params;

}
