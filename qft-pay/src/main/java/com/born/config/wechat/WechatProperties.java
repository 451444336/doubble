package com.born.config.wechat;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;
/**
 * 
* @ClassName: WechatProperties 
* @Description: 
* @author lijie 
* @date 2018年5月14日 下午6:22:18 
*
 */
@Data
@PropertySource("classpath:wechat.properties")
@ConfigurationProperties(prefix = "wechat")
@Component
public class WechatProperties {
	/**
	 * 服务号应用密码
	 */
	private String secret;
	/**
	 * 服务号应用ID
	 */
	private String appid;
	/**
	 * 商户号
	 */
	private String mchid;
	/**
	 * API密钥(商户密钥)
	 */
	private String apikey;
	/**
	 * 视图跳转地址
	 */
	private String viewUrl;
	/**
	 * 微信 网页授权地址
	 */
	private String jumpAuthorize;
	/**
	 * 微信支付异步回调地址
	 */
	private String async;
	/**
	 * 微信网页授权回调地址
	 */
	private String authorize;
	/**
	 * 微信公众平台Token
	 */
	private String token;
}
