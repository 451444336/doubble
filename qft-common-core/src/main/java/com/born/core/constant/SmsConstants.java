package com.born.core.constant;

public interface SmsConstants {

	/**
	 * 云片网
	 * 
	 * @ClassName: Ypw
	 * @Description: 枚举,与配置文件名对应
	 * @author 张永胜
	 * @date 2018年5月16日 上午11:15:11
	 * @version 1.0
	 */
	enum Ypw implements SmsConstants {

		URI_GET_USER_INFO("ypw_uri_get_user_info"),
		URI_SEND_SMS("ypw_uri_send_sms"), 
		URI_TPL_SEND_SMS("ypw_uri_tpl_send_sms"), 
		URI_SEND_VOICE("ypw_uri_send_voice"), 
		URI_SEND_BIND("ypw_uri_send_bind"), 
		URI_SEND_UNBIND("ypw_uri_send_unbind");

		private final String value;

		Ypw(String value) {
			this.value = value;
		}

		@Override
		public String getValue() {
			return value;
		}
	}

	/**
	 * 启瑞云
	 * 
	 * @ClassName: Qry
	 * @Description: 枚举,与配置文件名对应
	 * @author 张永胜
	 * @date 2018年5月16日 上午11:15:27
	 * @version 1.0
	 */
	enum Qry implements SmsConstants {

		URI_SEND_SMS("qry_uri_send_sms");

		private final String value;

		Qry(String value) {
			this.value = value;
		}

		@Override
		public String getValue() {
			return value;
		}

	}

	/**
	 * 请求方式
	 * 
	 * @ClassName: RequestMethod
	 * @Description: 枚举
	 * @author 张永胜
	 * @date 2018年5月16日 上午11:15:41
	 * @version 1.0
	 */
	enum RequestMethod implements SmsConstants {

		POST("POST"), GET("GET"), PUT("PUT");

		private final String method;

		RequestMethod(String method) {
			this.method = method;
		}

		@Override
		public String getValue() {
			return method;
		}
	}

	/**
	 * 
	 * @Title: getValue
	 * @Description: 提供给外部获取值
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月16日 上午10:58:55
	 */
	String getValue();
}
