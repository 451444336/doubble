package com.born.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 
* @ClassName: DubboProviderConfig  
* @Description: dubbo服务提供端配置
* @author lijie
* @date 2018年4月25日  
*
 */
@Configuration
@DubboComponentScan("com.born")
@PropertySource(value="classpath:dubbo.properties")
@Data
public class DubboProviderConfig {

    @Value("${dubbo.application.name}")
    private String applicationName;

    @Value("${dubbo.registry.protocol}")
    private String protocol;

    @Value("${dubbo.registry.address}")
    private String registryAddress;

    @Value("${dubbo.protocol.name}")
    private String protocolName;

    @Value("${dubbo.protocol.port}")
    private int protocolPort;

    @Value("${dubbo.provider.timeout}")
    private int timeout;

    @Value("${dubbo.provider.retries}")
    private int retries;

    @Value("${dubbo.provider.delay}")
    private int delay;

	/**
	 * 
	* @Title: applicationConfig  
	* @Description:注入dubbo上下文
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
		return applicationConfig;
	}

  /**
   * 
  * @Title: registryConfig  
  * @Description: 注入dubbo注册中心配置,基于zookeeper 
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
        registry.setCheck(true);
        return registry;
    }

   /**
    * 
   * @Title: protocolConfig  
   * @Description: 默认基于dubbo协议提供服务  
   * @param @return    参数  
   * @return ProtocolConfig    返回类型  
   * @author lijie
   * @throws
    */
    @Bean
    public ProtocolConfig protocolConfig() {
        // 服务提供者协议配置
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(protocolName);
        protocolConfig.setPort(protocolPort);
        protocolConfig.setThreads(200);
        protocolConfig.setSerialization("kryo");
        return protocolConfig;
    }

    /**
     * 
    * @Title: providerConfig  
    * @Description: 提供者配置 
    * @param @param applicationConfig
    * @param @param registryConfig
    * @param @param protocolConfig
    * @param @return    参数  
    * @return ProviderConfig    返回类型  
    * @author lijie
    * @throws
     */
    @Bean(name="permissionProvider")
    public ProviderConfig providerConfig(ApplicationConfig applicationConfig, RegistryConfig registryConfig, ProtocolConfig protocolConfig) {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setTimeout(timeout);
        providerConfig.setRetries(retries);
        providerConfig.setDelay(delay);
        providerConfig.setApplication(applicationConfig);
        providerConfig.setRegistry(registryConfig);
        providerConfig.setProtocol(protocolConfig);
        return providerConfig;
    }
}
