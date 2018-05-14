package com.born.util.constants;
/**
 * 
* @ClassName: HttpConstants  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author lijie
* @date 2018年4月25日  
*
 */
public interface HttpConstants {
	/**
	 * http 成功状态码
	 */
	int SUCCEE_CODE = 200;
	/**
	 * 默认编码
	 */
	String DEFAULT_ENCODING = "UTF-8";
	/**
	 * 设置连接超时时间，单位毫秒
	 */
	int CONNECT_TIMEOUT = 10000;
	/**
	 * 设置从connect Manager获取Connection 超时时间，单位毫秒
	 */
	int REQUEST_TIMEOUT = 10000;
	/**
	 * 请求获取数据的超时时间，单位毫秒
	 */
	int SOCKET_TIMEOUT = 10000;
}
