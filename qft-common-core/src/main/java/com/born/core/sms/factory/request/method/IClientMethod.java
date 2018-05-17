package com.born.core.sms.factory.request.method;

import java.util.Map;

/**
 * 可以扩张其他请求方式和实现
 * 
 * @ClassName: IClientMethod
 * @Description: 请求方式
 * @author 张永胜
 * @date 2018年5月14日 下午5:52:45
 * @version 1.0
 */
public interface IClientMethod {

	/***
	 * 
	 * @Title: post 请求
	 * @Description: HttpClient请求方式
	 * @param url
	 *            请求地址
	 * @param params
	 *            参数
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月14日 下午5:53:43
	 */
	String post(String url, Map<String, String> params);

	/**
	 * 
	 * @Title: get 请求
	 * @Description: HttpClient请求方式
	 * @param url
	 *            请求地址
	 * @param params
	 *            参数
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月15日 下午2:56:59
	 */
	String get(String url, Map<String, String> params);

	/**
	 * 
	 * @Title: put 请求
	 * @Description: HttpClient请求方式
	 * @param url
	 *            请求地址
	 * @param params
	 *            参数
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月16日 上午10:29:56
	 */
	String put(String url, Map<String, String> params);
}
