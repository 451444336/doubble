package com.born.schedule;

import org.springframework.stereotype.Component;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

import lombok.extern.slf4j.Slf4j;
/**
 * 
* @ClassName: Test 
* @Description: 任务调度demo
* @author lijie 
* @date 2018年5月14日 下午5:40:28 
*
 */
@Slf4j
@Component
@JobHandler(value = "test")
public class Test extends IJobHandler {
	
	
	@Override
	public ReturnT<String> execute(String param) throws Exception {
		log.info("任务执行开始");
		try {
		} catch (Exception e) {
			log.error("任务执行异常", e);
			return FAIL;
		}
		return SUCCESS;
	}
}