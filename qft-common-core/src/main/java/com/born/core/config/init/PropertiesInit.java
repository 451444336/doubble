package com.born.core.config.init;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Order(1)
@Configuration
public class PropertiesInit {

	public PropertiesInit() {
		PropertiesListenerConfig.loadAllProperties("rsa-key.properties", 1);
		PropertiesListenerConfig.loadAllProperties("sms.properties", 2);
	}
}
