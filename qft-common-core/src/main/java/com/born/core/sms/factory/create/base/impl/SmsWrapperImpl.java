package com.born.core.sms.factory.create.base.impl;

import org.apache.commons.lang3.StringUtils;

import com.born.core.constant.PlatformApikey;
import com.born.core.constant.PropertiesConstants;
import com.born.core.constant.SmsConstants;
import com.born.core.constant.SmsConstants.RequestMethod;
import com.born.core.sms.factory.create.base.entity.SmsBody;
import com.born.core.sms.factory.request.Client;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @ClassName: SmsWrapperImpl
 * @Description: 请求实现
 * @author 张永胜
 * @date 2018年5月16日 上午10:41:20
 * @version 1.0
 */
@Slf4j
public class SmsWrapperImpl {

	/**
	 * 请求对象
	 */
	private static SmsWrapper instance;

	public static SmsWrapper getInstance(SmsBody sms) {
		if (instance == null) {
			synchronized (SmsWrapper.class) {
				if (instance == null) {
					instance = new SmsWrapper() {

						@Override
						public String send() {
							log.info("短信请求参数 {}", sms.getMethod(), sms.getUrl(), sms.getParams().toString());
							return request(sms, sms.getMethod());
						}

						@Override
						public String send(PlatformApikey platformApikey) {
							log.info("短信请求参数 {}", sms.getMethod(), sms.getUrl(), sms.getParams().toString());
							sms.getParams().put(platformApikey.getKey()[0],
									PropertiesConstants.PROPERTIES_MAP.get(platformApikey.getValue()[0]));
							/**
							 * 验证空，是有些平台测试是必须用密码，但有些不会用密码
							 */
							if (StringUtils.isNotBlank(platformApikey.getValue()[1])) {
								sms.getParams().put(platformApikey.getKey()[1],
										PropertiesConstants.PROPERTIES_MAP.get(platformApikey.getValue()[1]));
							}
							return request(sms, sms.getMethod());
						}

						@Override
						public String send(SmsConstants.RequestMethod smsConstants) {
							log.info("短信请求参数 {}", sms.getMethod(), sms.getUrl(), sms.getParams().toString());
							return request(sms, smsConstants.getValue());
						}

						@Override
						public String send(RequestMethod smsConstants, PlatformApikey platformApikey) {
							log.info("短信请求参数 {}", sms.getMethod(), sms.getUrl(), sms.getParams().toString());
							sms.getParams().put(platformApikey.getKey()[0],
									PropertiesConstants.PROPERTIES_MAP.get(platformApikey.getValue()[0]));
							/**
							 * 验证空，是有些平台测试是必须用密码，但有些不会用密码
							 */
							if (StringUtils.isNotBlank(platformApikey.getValue()[1])) {
								sms.getParams().put(platformApikey.getKey()[1],
										PropertiesConstants.PROPERTIES_MAP.get(platformApikey.getValue()[1]));
							}
							return request(sms, smsConstants.getValue());
						}

					};
				}
			}
		}
		return instance;
	}

	/**
	 * 
	 * @Title: request
	 * @Description: 公共的请求方式选择
	 * @param sms
	 * @param requestMethod
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月17日 上午9:31:31
	 */
	private static String request(SmsBody sms, String requestMethod) {
		switch (requestMethod) {
		case "POST":
			return Client.getInstance().post(sms.getUrl(), sms.getParams());
		case "GET":
			return Client.getInstance().get(sms.getUrl(), sms.getParams());
		case "PUT":
			return Client.getInstance().put(sms.getUrl(), sms.getParams());
		}
		return Client.getInstance().post(sms.getUrl(), sms.getParams());
	}

}
