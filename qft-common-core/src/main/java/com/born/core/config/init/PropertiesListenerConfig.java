package com.born.core.config.init;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.born.core.constant.PropertiesConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @ClassName: PropertiesListenerConfig
 * @Description: 读取配置文件类
 * @author 张永胜
 * @date 2018年5月11日 下午3:01:28
 * @version 1.0
 */
@Slf4j
public class PropertiesListenerConfig {

	/**
	 * 
	 * @Title: processProperties
	 * @Description: 封装配置文件信息
	 * @param props
	 * @throws BeansException
	 * @author 张永胜
	 * @return void
	 * @date 2018年5月11日 下午2:58:18
	 */
	private static void processProperties(Properties props, int flag) throws BeansException {
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			try {
				/**
				 * PropertiesLoaderUtils的默认编码是ISO-8859-1,在这里转码一下
				 */
				switch (flag) {
				case 1:// 秘钥对
					PropertiesConstants.PROPERTIES_RSA_KEY_MAP.put(keyStr, new String(props.getProperty(keyStr).getBytes("ISO-8859-1"), "utf-8"));
					break;
				case 2:// 短信
					PropertiesConstants.PROPERTIES_SMS_MAP.put(keyStr,
							new String(props.getProperty(keyStr).getBytes("ISO-8859-1"), "utf-8"));
					break;
				default:
					break;
				}
				
			} catch (UnsupportedEncodingException e) {
				log.error("启动时读取配置文件，转换成 Unicode 异常", e);
			} catch (Exception e) {
				log.error("启动时读取配置文件异常", e);
			}
		}
	}

	/**
	 * 
	 * @Title: loadAllProperties
	 * @Description: 加载所有配置
	 * @param propertyFileName
	 *            文件名称
	 * @author 张永胜
	 * @return void
	 * @date 2018年5月11日 下午3:31:35
	 */
	public static void loadAllProperties(String propertyFileName, int flag) {
		try {
			Properties properties = PropertiesLoaderUtils.loadAllProperties(propertyFileName);
			processProperties(properties, flag);
		} catch (IOException e) {
			log.error("启动时读取配置文件IO异常", e);
		}
	}

}