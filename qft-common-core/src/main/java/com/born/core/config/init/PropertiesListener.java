package com.born.core.config.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 
 * @ClassName: PropertiesListener
 * @Description: 配置文件监听类
 * @author 张永胜
 * @date 2018年5月11日 下午3:09:07
 * @version 1.0
 */
@WebListener
public class PropertiesListener implements ServletContextListener {

	/**
	 * 配置文件路径，一般都在resources目录下
	 */
	private String propertyFileName = "rsa-key.properties";

	public PropertiesListener() {
	}

	/**
	 * 带文件路径参数构造方法
	 */
	public PropertiesListener(String propertyFileName) {
		this.propertyFileName = propertyFileName;
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		PropertiesListenerConfig.loadAllProperties(propertyFileName);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
}