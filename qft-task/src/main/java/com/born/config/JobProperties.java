package com.born.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 
* @ClassName: JobProperties 
* @Description:调度任务配置
* @author lijie 
* @date 2018年5月14日 下午5:35:16 
*
 */
@Data
@Configuration
@PropertySource("classpath:job.properties")
public class JobProperties {

    @Value("${xxl.job.admin.addresses}")
    private String addresses;

    @Value("${xxl.job.executor.appname}")
    private String appname;

    @Value("${xxl.job.executor.ip}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private Integer port;

    @Value("${xxl.job.executor.logpath}")
    private String logpath;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

}
