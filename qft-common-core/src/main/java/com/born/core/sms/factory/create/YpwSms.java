package com.born.core.sms.factory.create;

import java.util.HashMap;
import java.util.Map;

import com.born.core.constant.PlatformApikey;
import com.born.core.constant.PropertiesConstants;
import com.born.core.constant.SmsConstants;
import com.born.core.sms.factory.create.base.entity.SmsBody;
import com.born.core.sms.factory.create.base.impl.SmsWrapper;
import com.born.core.sms.factory.create.base.impl.SmsWrapperImpl;

/**
 * 云片网
 * 
 * @ClassName: YpSms
 * @Description: 云片网短信服务
 * @author 张永胜
 * @date 2018年5月15日 下午3:30:45
 * @version 1.0
 */
public class YpwSms extends SmsBody {

	/**
	 * 这里必须指定
	 * 如果不指定，默认send 是post发送
	 */
	public YpwSms() {
		setMethod(SmsConstants.RequestMethod.GET.getValue());
	}

	/**
	 * 自定义传入参数
	 * 
	 * @Title: content
	 * @Description: 可以传入请求中所有的参数
	 * @param params
	 * @return
	 * @author 张永胜
	 * @return SmsWrapper
	 * @date 2018年5月16日 下午4:47:10
	 */
	public SmsWrapper setContent(Map<String, String> params) {
		setParams(params);
		setUrl(PropertiesConstants.PROPERTIES_MAP.get(SmsConstants.Ypw.URI_SEND_SMS.getValue()));
		return SmsWrapperImpl.getInstance(this);
	}

	/**
	 * 普通信息发送
	 * 
	 * @Title: content
	 * @Description: 发送普通信息
	 * @param mobile
	 * @param text
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月16日 上午9:25:53
	 */
	public SmsWrapper setContent(String mobile, String text) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("apikey", PropertiesConstants.PROPERTIES_MAP.get(PlatformApikey.Ypw.APIKEY.getValue()[0]));
		params.put("text", text);
		params.put("mobile", mobile);
		setParams(params);
		setUrl(PropertiesConstants.PROPERTIES_MAP.get(SmsConstants.Ypw.URI_SEND_SMS.getValue()));
		return SmsWrapperImpl.getInstance(this);
	}

	/**
	 * 
	 * @Title: tplSendSms
	 * @Description: 通过模板发送短信(不推荐)
	 * @param tpl_id
	 *            模板id
	 * @param tpl_value
	 *            模板变量值
	 * @param mobile
	 *            接受的手机号
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月15日 下午5:59:44
	 */
	public SmsWrapper setContentTplSms(long tpl_id, String tpl_value, String mobile) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("apikey", PropertiesConstants.PROPERTIES_MAP.get(PlatformApikey.Ypw.APIKEY.getValue()[0]));
		params.put("tpl_id", String.valueOf(tpl_id));
		params.put("tpl_value", tpl_value);
		params.put("mobile", mobile);
		setParams(params);
		setUrl(PropertiesConstants.PROPERTIES_MAP.get(SmsConstants.Ypw.URI_TPL_SEND_SMS.getValue()));
		return SmsWrapperImpl.getInstance(this);
	}

	/**
	 * 
	 * @Title: sendVoice
	 * @Description: 通过接口发送语音验证码
	 * @param mobile
	 *            接受的手机号
	 * @param code
	 *            验证码
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月15日 下午6:00:27
	 */
	public SmsWrapper setContentVoice(String mobile, String code) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("apikey", PropertiesConstants.PROPERTIES_MAP.get(PlatformApikey.Ypw.APIKEY.getValue()[0]));
		params.put("mobile", mobile);
		params.put("code", code);
		setParams(params);
		setUrl(PropertiesConstants.PROPERTIES_MAP.get(SmsConstants.Ypw.URI_SEND_VOICE.getValue()));
		return SmsWrapperImpl.getInstance(this);
	}

	/**
	 * 
	 * @Title: bindCall
	 * @Description: 通过接口绑定主被叫号码
	 * @param from
	 *            主叫
	 * @param to
	 *            被叫
	 * @param duration
	 *            有效时长，单位：秒
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月15日 下午6:00:46
	 */
	public SmsWrapper setContentBindCall(String from, String to, Integer duration) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("apikey", PropertiesConstants.PROPERTIES_MAP.get(PlatformApikey.Ypw.APIKEY.getValue()[0]));
		params.put("from", from);
		params.put("to", to);
		params.put("duration", String.valueOf(duration));
		setParams(params);
		setUrl(PropertiesConstants.PROPERTIES_MAP.get(SmsConstants.Ypw.URI_SEND_BIND.getValue()));
		return SmsWrapperImpl.getInstance(this);
	}

	/**
	 * 
	 * @Title: unbindCall
	 * @Description: 通过接口解绑绑定主被叫号码
	 * @param from
	 *            主叫
	 * @param to
	 *            被叫
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月15日 下午6:01:06
	 */
	public SmsWrapper setContentUnbindCall(String from, String to) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("apikey", PropertiesConstants.PROPERTIES_MAP.get(PlatformApikey.Ypw.APIKEY.getValue()[0]));
		params.put("from", from);
		params.put("to", to);
		setParams(params);
		setUrl(PropertiesConstants.PROPERTIES_MAP.get(SmsConstants.Ypw.URI_SEND_UNBIND.getValue()));
		return SmsWrapperImpl.getInstance(this);
	}

}
