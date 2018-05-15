package com.born.config;

import com.xxl.job.core.executor.XxlJobExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
* @ClassName: JobConfig 
* @Description:调度任务包配置 
* @author lijie 
* @date 2018年5月14日 下午5:35:34 
*
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = "com.born.schedule")
public class JobConfig {

	@Autowired
	private JobProperties jobProperties;

	@Bean(initMethod = "start", destroyMethod = "destroy")
	public XxlJobExecutor xxlJobExecutor() {
		log.info("xxl-job config init.");
		log.info("获取的参数：" + jobProperties.getIp() + "注册地址：" + jobProperties.getAddresses());
		XxlJobExecutor xxlJobExecutor = new XxlJobExecutor();
		xxlJobExecutor.setIp(jobProperties.getIp());
		xxlJobExecutor.setPort(jobProperties.getPort());
		xxlJobExecutor.setAppName(jobProperties.getAppname());
		xxlJobExecutor.setAdminAddresses(jobProperties.getAddresses());
		xxlJobExecutor.setLogPath(jobProperties.getLogpath());
		xxlJobExecutor.setAccessToken(jobProperties.getAccessToken());
		return xxlJobExecutor;
	}

}
