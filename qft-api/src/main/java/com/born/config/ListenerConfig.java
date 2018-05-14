package com.born.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.born.core.config.init.PropertiesListener;

/**
 * 
 * @ClassName: ListenerConfig
 * @Description: 监听器
 * @author 张永胜
 * @date 2018年5月11日 下午3:10:31
 * @version 1.0
 */
@Configuration
public class ListenerConfig {

	/**
	 * 
	 * @Title: servletListenerRegistrationBean
	 * @Description:这里是的读取RSA密钥
	 * @return
	 * @author 张永胜
	 * @return ServletListenerRegistrationBean<PropertiesListener>
	 * @date 2018年5月11日 下午3:11:02
	 */
	@Bean
	public ServletListenerRegistrationBean<PropertiesListener> servletListenerRegistrationBean() {
		ServletListenerRegistrationBean<PropertiesListener> slrBean = new ServletListenerRegistrationBean<PropertiesListener>();
		slrBean.setListener(new PropertiesListener());
		return slrBean;
	}
}