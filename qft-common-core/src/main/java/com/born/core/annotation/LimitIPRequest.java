package com.born.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * 接口访问限制
 * 
 * @ClassName: LimitIPRequest
 * @Description: 限制某时间段内可以访问的次数
 * @author 张永胜
 * @date 2018年5月10日 上午10:41:37
 * @version 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Order(Ordered.HIGHEST_PRECEDENCE) // 设置顺序为最高优先级
public @interface LimitIPRequest {

	/**
	 * 
	 * @Title: limitCounts
	 * @Description: 限制某时间段内可以访问的次数，默认设置100
	 * @return
	 * @author 张永胜
	 * @return int
	 * @date 2018年5月10日 上午9:49:20
	 */
	int limitCounts() default 30;

	/**
	 * 
	 * @Title: timeSecond
	 * @Description: 限制访问的某一个时间段，单位为秒，默认值1分钟即可
	 * @return
	 * @author 张永胜
	 * @return int
	 * @date 2018年5月10日 上午9:49:36
	 */
	int timeSecond() default 60;

}