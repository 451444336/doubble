package com.born;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

/**
 * 
 * @ClassName: ApiApplication
 * @Description: spring boot start config
 * @author lijie
 * @date 2018年4月25日
 *
 */
@SpringBootApplication
@DubboComponentScan(basePackages = "com.born.facade.service")
public class TaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}
	
	@Bean
	public EmbeddedServletContainerFactory servletContainer(){
		TomcatEmbeddedServletContainerFactory factory=new TomcatEmbeddedServletContainerFactory();
		factory.setPort(20010);
		return factory;
	}
}
