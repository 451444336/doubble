package com.born.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

import lombok.Data;

/**
 * 
* @ClassName: DubboConsumerConfig  
* @Description: 消费端配置
* @author lijie
* @date 2018年4月25日  
*
 */
@Configuration
@DubboComponentScan
@PropertySource(value = "classpath:dubbo-consumer.properties")
@Data
public class DubboConsumerConfig {

    @Value("${dubbo.application.name}")
    private String applicationName;

    @Value("${dubbo.registry.protocol}")
    private String protocol;

    @Value("${dubbo.registry.address}")
    private String registryAddress;

    @Value("${dubbo.consumer.timeout}")
    private int timeout;

    @Value("${dubbo.consumer.retries}")
    private int retries;
    
    /**
     * 
    * @Title: applicationConfig  
    * @Description: applicationConfig 的描述：注入dubbo上下文 
    * @param @return    参数  
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
    * @Title: consumerConfig  
    * @Description: TODO(这里用一句话描述这个方法的作用)  
    * @param @return    参数  
    * @return ConsumerConfig    返回类型  
    * @author lijie
    * @throws
     */
	@Bean
	public ConsumerConfig consumerConfig() {
		ConsumerConfig consumerConfig = new ConsumerConfig();
		consumerConfig.setTimeout(this.timeout);
		// 设置启动时不检查注册中心
		consumerConfig.setCheck(false);
		return consumerConfig;
    }


    /**
     * 
    * @Title: registryConfig  
    * @Description: registryConfig 的描述：注入dubbo注册中心配置,基于zookeeper
    * @param @return    参数  
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
