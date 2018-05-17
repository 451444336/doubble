package com.born.core.sms.factory.create.base;

/**
 * 多个平台公共的接口方法
 * 
 * @ClassName: SendService
 * @Description: 短信的共同接口
 * @author 张永胜
 * @date 2018年5月15日 下午3:27:09
 * @version 1.0
 */
public interface SendSmsService {

	/**
	 * 
	 * 公共请求必须实现的方法
	 * 
	 * @Title: send
	 * @Description: 多个平台通用默认的发送接口
	 * @return
	 * @author 张永胜
	 * @return String
	 * @date 2018年5月16日 上午9:09:13
	 */
	public String send();

}
