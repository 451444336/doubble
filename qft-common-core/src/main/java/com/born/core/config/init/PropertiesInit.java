package com.born.core.config.init;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 
* @ClassName: PropertiesInit 
* @Description: 初始化配置文件
* @author 张永胜 
* @date 2018年5月17日 下午2:14:48 
* @version 1.0
 */
@Order(1)
@Configuration
public class PropertiesInit {

	public PropertiesInit() {
		PropertiesListenerConfig.loadAllProperties("rsa-key.properties", 1);
		PropertiesListenerConfig.loadAllProperties("sms.properties", 2);
	}
}
