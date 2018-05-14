package com.born.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 
* @ClassName: DubboConsumer 
* @Description: dubbo消费端配置 
* @author lijie 
* @date 2018年4月25日 下午4:17:59 
*
 */
@Configuration
@DubboComponentScan(basePackages = "com.born")
@PropertySource(value = "classpath:dubbo-consumer.properties")
@Data
public class DubboConsumer {

    @Value("${dubbo.application.name}")
    private String applicationName;

    @Value("${dubbo.registry.protocol}")
    private String protocol;

    @Value("${dubbo.registry.address}")
    private String registryAddress;

   /**
    * 
   * @Title: applicationConfig 
   * @Description: applicationConfig 的描述：注入dubbo上下文 
   * @param @return    设定文件 
   * @return ApplicationConfig    返回类型 
   * @author lijie
   * @throws
    */
	@Bean
	public ApplicationConfig applicationConfig() {
		// 当前应用配置
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName(this.applicationName);
		applicationConfig.setLogger("slf4j");
		return applicationConfig;
	}

   /**
    * 
   * @Title: registryConfig 
   * @Description: registryConfig 的描述：注入dubbo注册中心配置,基于zookeeper
   * @param @return    设定文件 
   * @return RegistryConfig    返回类型 
   * @author lijie
   * @throws
    */
	@Bean
	public RegistryConfig registryConfig() {
		// 连接注册中心配置
		RegistryConfig registry = new RegistryConfig();
		registry.setProtocol(protocol);
		registry.setAddress(registryAddress);
		return registry;
	}
}
