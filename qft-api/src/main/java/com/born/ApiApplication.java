package com.born;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
}
