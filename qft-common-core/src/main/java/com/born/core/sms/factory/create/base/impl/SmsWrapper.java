package com.born.core.sms.factory.create.base.impl;

import com.born.core.constant.PlatformApikey;
import com.born.core.constant.SmsConstants;
import com.born.core.sms.factory.create.base.SendSmsService;

/**
 * 
 * 抽象方式类
 * 
 * 如果多个平台，有相同的接口，可以写在这个适配类里
 * 
 * @ClassName: SmsWrapper
 * @Description: 适配多个短信平台不同的接口处理方法
 * @author 张永胜
 * @date 2018年5月15日 下午5:14:52
 * @version 1.0
 */
public abstract class SmsWrapper implements SendSmsService {

	/**
	 * 默认请求发送
	 */
	public String send() {
		return "";
	};

	/**
	 * 
	 * @Title: send
	 * @Description: 默认post请求发送
	 * @param platformApikey
	 *            平台对应的账户ApiKey
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月16日 下午7:10:21
	 */
	public String send(PlatformApikey platformApikey) {
		return "";
	};

	/**
	 * 
	 * @Title: send
	 * @Description: 请求方式
	 * @param requestMethod
	 *            请求方式
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月16日 下午7:08:49
	 */
	public String send(SmsConstants.RequestMethod requestMethod) {
		return "";
	};

	/**
	 * 
	 * @Title: send
	 * @Description: 请求方式+对应平台多个ApiKey
	 * @param requestMethod
	 *            请求方式
	 * @param platformApikey
	 *            平台对应的账户ApiKey
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月16日 下午7:08:10
	 */
	public String send(SmsConstants.RequestMethod requestMethod, PlatformApikey platformApikey) {
		return "";
	};

}
