package com.born.core.constant;

/**
 * 短信平台多个ApiKey情况
 * 
 * @ClassName: PlatformApikey
 * @Description: 短信平台多个ApiKey情况
 * @author 张永胜
 * @date 2018年5月16日 下午7:23:15
 * @version 1.0
 */
public interface PlatformApikey {

	/**
	 * 启瑞云
	 * 
	 * @ClassName: Qry
	 * @Description: 读取当前平台下对应的ApiKey
	 * @author 张永胜
	 * @date 2018年5月16日 下午7:05:20
	 * @version 1.0
	 */
	enum Qry implements PlatformApikey {

		/** 重庆航畅科技 */
		HC_APIKEY("qry_hc_apikey", "qry_hc_apisecret"),
		/** 重庆新凯科技 */
		XK_APIKEY("qry_xk_apikey", "qry_xk_apisecret"),
		/** 重庆全房通 */
		QFT_APIKEY("qry_qft_apikey", "qry_qft_apisecret");

		private String[] v = null;
		private String[] k = { "nu", "pw" };//顺序与v值顺序对应

		Qry(String apikey, String apisecret) {
			v[0] = apikey;
			v[1] = apisecret;
		}

		@Override
		public String[] getKey() {
			return k;
		}

		@Override
		public String[] getValue() {
			return v;
		}

	}

	/**
	 * 云片网
	 * 
	 * @ClassName: Ypw
	 * @Description: 读取当前平台下对应的ApiKey
	 * @author 张永胜
	 * @date 2018年5月16日 下午7:05:58
	 * @version 1.0
	 */
	enum Ypw implements PlatformApikey {

		/** 重庆航畅科技 */
		APIKEY("ypw_apikey", null);

		private String[] v = null;
		private String[] k = { "apikey", "" };//顺序与v值顺序对应

		Ypw(String apikey, String apisecret) {
			v[0] = apikey;
			v[1] = apisecret;
		}

		@Override
		public String[] getKey() {
			return k;
		}

		@Override
		public String[] getValue() {
			return v;
		}
	}

	/**
	 * 
	 * @Title: getKey
	 * @Description: 获取Key
	 * @return
	 * @author 张永胜
	 * @return String[]
	 * @date 2018年5月16日 下午7:35:25
	 */
	String[] getKey();

	/**
	 * 
	 * @Title: getValue
	 * @Description: 获取选择的值
	 * @return
	 * @author 张永胜
	 * @return String[]
	 * @date 2018年5月16日 下午7:35:38
	 */
	String[] getValue();

}
