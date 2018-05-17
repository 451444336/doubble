package com.born.core.sms;

import com.born.core.sms.factory.create.QrySms;
import com.born.core.sms.factory.create.YpwSms;

/**
 * 创建多个短信服务静态工厂
 * 
 * @ClassName: SendFactory
 * @Description: 发送短信
 * @author 张永胜
 * @date 2018年5月14日 下午5:22:56
 * @version 1.0
 */
public class SmsApi {

	private static YpwSms instanceYpwSms;
	private static QrySms instanceQrySms;

	/**
	 * 云片网
	 * 
	 * @Title: createYpwSms
	 * @Description: 创建云片网短信服务
	 * @return
	 * @author 张永胜
	 * @return SendSmsService
	 * @date 2018年5月15日 下午5:20:04
	 */
	public static YpwSms createYpwSms() {
		if (instanceYpwSms == null) {
			synchronized (YpwSms.class) {
				if (instanceYpwSms == null) {
					instanceYpwSms = new YpwSms();
				}
			}
		}
		return instanceYpwSms;
	}

	/**
	 * 启瑞云,默认会是航畅科技ApiKey发送
	 * 
	 * @Title: createQrySms
	 * @Description: 创建启瑞云短信服务
	 * @return
	 * @author 张永胜
	 * @return SendSmsService
	 * @date 2018年5月15日 下午5:20:25
	 */
	public static QrySms createQrySms() {
		if (instanceQrySms == null) {
			synchronized (QrySms.class) {
				if (instanceQrySms == null) {
					instanceQrySms = new QrySms();
				}
			}
		}
		return instanceQrySms;
	}

}
