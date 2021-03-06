package com.born;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
/**
 * 
* @ClassName: BasicbusinessServiceApplication  
* @Description: spring boot start config
* @author lijie
* @date 2018年4月25日  
*
 */
@MapperScan(basePackages = "com.born.mapper")
@SpringBootApplication
public class BasicbusinessServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicbusinessServiceApplication.class, args);
	}
	
	/**
	 * 设置tomcat端口号
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		factory.setPort(20012);
		return factory;
	}
}
