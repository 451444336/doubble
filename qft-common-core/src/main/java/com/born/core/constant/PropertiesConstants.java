package com.born.core.constant;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 
 * 读取所有的配置文件
 * 
 * @ClassName: KeyConstants
 * @Description: 读取配置文件常量
 * @author 张永胜
 * @date 2018年5月11日 下午2:56:21
 * @version 1.0
 */
public interface PropertiesConstants {

	/**
	 * 秘钥信息
	 */
	static HashMap<String, String> PROPERTIES_RSA_KEY_MAP = new LinkedHashMap<String, String>(4);

	/**
	 * 短信信息
	 */
	static HashMap<String, String> PROPERTIES_SMS_MAP = new LinkedHashMap<String, String>();
}
