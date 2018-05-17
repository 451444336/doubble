package com.born.core.sms.factory.create;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.born.core.constant.PlatformApikey;
import com.born.core.constant.PropertiesConstants;
import com.born.core.constant.SmsConstants;
import com.born.core.sms.factory.create.base.entity.SmsBody;
import com.born.core.sms.factory.create.base.impl.SmsWrapper;
import com.born.core.sms.factory.create.base.impl.SmsWrapperImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * 启瑞云
 * 
 * @ClassName: QdySms
 * @Description: 启瑞云短信服务
 * @author 张永胜
 * @date 2018年5月15日 下午3:29:50
 * @version 1.0
 */
@Slf4j
public class QrySms extends SmsBody {

	/**
	 * 这里必须指定 如果不指定，默认send 是post发送
	 */
	public QrySms() {
		setMethod(SmsConstants.RequestMethod.POST.getValue());
	}

	/**
	 * 自定义传入参数
	 * 
	 * @Title: sendMsg
	 * @Description: 可以传入请求中所有的参数
	 * @param params
	 *            参数值
	 * @return
	 * @author 张永胜
	 * @return SmsWrapper
	 * @date 2018年5月16日 下午7:56:14
	 */
	public SmsWrapper setContent(Map<String, String> params) {
		return SmsWrapperImpl.getInstance(this);
	}

	/**
	 * 默认get请求
	 * 
	 * @Title: content
	 * @Description: 普通请求发送信息
	 * @param mobile
	 *            手机号
	 * @param text
	 *            发送内容
	 * @return
	 * @author 张永胜
	 * @return SmsWrapper
	 * @date 2018年5月16日 下午7:56:38
	 */
	public SmsWrapper setContent(String mobile, String text) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("dc", String.valueOf(15));
		try {
			params.put("sm", URLEncoder.encode(text, "utf8"));// 由 tf 指定的编码后的内容
		} catch (UnsupportedEncodingException e) {
			log.error("转换编码异常", e);
		}
		params.put("da", mobile);// 手机号码，多个号码用分号(半角)分割。请不要超过 100 个号码
		params.put("un", PropertiesConstants.PROPERTIES_MAP.get(PlatformApikey.Qry.HC_APIKEY.getValue()[0]));// 用户名
		/**
		 * 密码（请勿在生产环境中直接使用，如需直接使用请联系绑定IP地址）
		 */
		params.put("pw", PropertiesConstants.PROPERTIES_MAP.get(PlatformApikey.Qry.HC_APIKEY.getValue()[1]));
		params.put("tf", String.valueOf(3));// 短信内容的编码，默认为 0 表示 HEX 格式，见上面内容编码说明
		params.put("rd", String.valueOf(1));// 是否需要状态报告。0 表示不需要；1 表示需要
		/**
		 * 控制返回格式 0 返回XML格式，各字段以&符号分割 1 返回XML格式 2 返回JSON格式, 可能会出现未定义字段，忽略即可。缺失字段则按默认处理。
		 */
		params.put("rf", String.valueOf(2));
		setParams(params);
		setUrl(PropertiesConstants.PROPERTIES_MAP.get(SmsConstants.Qry.URI_SEND_SMS.getValue()));
		return SmsWrapperImpl.getInstance(this);
	}

	/**
	 * 默认get请求
	 * 
	 * @Title: setContent
	 * @Description: 普通请求发送信息
	 * @param mobile
	 *            手机号
	 * @param text
	 *            发送内容
	 * @param rf
	 *            控制返回格式 0 返回XML格式，各字段以&符号分割 1 返回XML格式 2 返回JSON格式
	 * @return
	 * @author 张永胜
	 * @return SmsWrapper
	 * @date 2018年5月17日 上午9:56:12
	 */
	public SmsWrapper setContent(String mobile, String text, int rf) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("dc", String.valueOf(15));
		try {
			params.put("sm", URLEncoder.encode(text, "utf8"));// 由 tf 指定的编码后的内容
		} catch (UnsupportedEncodingException e) {
			log.error("转换编码异常", e);
		}
		params.put("da", mobile);// 手机号码，多个号码用分号(半角)分割。请不要超过 100 个号码
		params.put("un", PropertiesConstants.PROPERTIES_MAP.get(PlatformApikey.Qry.HC_APIKEY.getValue()[0]));// 用户名
		/**
		 * 密码（请勿在生产环境中直接使用，如需直接使用请联系绑定IP地址）
		 */
		params.put("pw", PropertiesConstants.PROPERTIES_MAP.get(PlatformApikey.Qry.HC_APIKEY.getValue()[1]));
		params.put("tf", String.valueOf(3));// 短信内容的编码，默认为 0 表示 HEX 格式，见上面内容编码说明
		params.put("rd", String.valueOf(1));// 是否需要状态报告。0 表示不需要；1 表示需要
		/**
		 * 控制返回格式 0 返回XML格式，各字段以&符号分割 1 返回XML格式 2 返回JSON格式, 可能会出现未定义字段，忽略即可。缺失字段则按默认处理。
		 */
		params.put("rf", String.valueOf(rf));
		setParams(params);
		setUrl(PropertiesConstants.PROPERTIES_MAP.get(SmsConstants.Qry.URI_SEND_SMS.getValue()));
		return SmsWrapperImpl.getInstance(this);
	}

}
