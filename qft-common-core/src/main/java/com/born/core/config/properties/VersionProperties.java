/**
 * @Title: WebProperties.java
 * @Package com.ph.shopping.common.core.config.properties
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 李杰
 * @date: 2017年6月29日 下午1:43:01
 * @version V1.0
 * @Copyright: 2017
 */
package com.born.core.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 
* @ClassName: DubboProperties 
* @Description: dubbo 相关属性配置 
* @author lijie 
* @date 2018年4月28日 上午10:38:07 
*
 */
@Data
@PropertySource("classpath:version.properties")
@ConfigurationProperties(prefix = "dubbo")
@Component
public class VersionProperties {
    /**
     * 服务版本
     */
    private String version;
}
