package com.born.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.born.core.config.properties.VersionProperties;
/**
 * 
* @ClassName: DubboAgent 
* @Description:dubbo代理 
* @author lijie 
* @date 2018年5月3日 上午8:46:22 
*
 */
@Component
public class DubboAgent {

	@Autowired
	private ApplicationConfig applicationConfig;

	@Autowired
	private RegistryConfig registryConfig;

	@Autowired
	private VersionProperties versionConfig;

    /**
     * 
    * @Title: getReference 
    * @Description: 获取dubbo代理对象
    * @param @param c
    * @param @return    设定文件 
    * @return T    返回类型 
    * @author lijie
    * @throws
     */
	public <T> T getReference(Class<T> c) {
		ReferenceConfig<T> reference = new ReferenceConfig<>();
		reference.setApplication(applicationConfig);
		reference.setRegistry(registryConfig);
		reference.setInterface(c);
		reference.setVersion(versionConfig.getVersion());
		return reference.get();
	}
}
