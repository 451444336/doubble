package com.born.core.aspectj;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.born.core.annotation.LimitIPRequest;
import com.born.core.exception.LimitIPRequestException;
import com.born.core.rediscache.ICacheService;
import com.born.util.constants.AppConstants;
import com.born.util.http.IPUtils;
import com.born.util.json.JsonResult;
import com.born.util.json.ResultCode;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @ClassName: LimitIPRequestDisplay
 * @Description: 接口访问限制切面实现类
 * @author 张永胜
 * @date 2018年5月10日 上午10:13:11
 * @version 1.0
 */
@Slf4j
@Aspect
@Component
public class LimitIPRequestAspect {

	@Autowired
	private ICacheService<String, Object> iCacheService;

	@Pointcut("execution(* com.born.controller..*.*(..)) && @annotation(com.born.core.annotation.LimitIPRequest)")
	public void before() {
	}

	/**
	 * 
	 * @Title: requestLimit
	 * @Description: 切面拦截接口请求
	 * @param joinPoint
	 * @return
	 * @throws Exception
	 * @author 张永胜
	 * @return Object
	 * @date 2018年5月10日 上午11:09:26
	 */
	@Around("before()")
	public Object requestLimit(ProceedingJoinPoint joinPoint) throws Exception {
		try {

			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			HttpServletRequest request = attributes.getRequest();

			/**
			 * 判断request不能为空
			 */
			if (request == null) {
				throw new LimitIPRequestException("HttpServletRequest错误");
			}

			/**
			 * 判断获取到的注解不能为空
			 */
			LimitIPRequest limit = this.getAnnotation(joinPoint);
			if (limit == null) {
				return null;
			}

			String ip = IPUtils.getIpAddress(request);
			String uri = request.getRequestURI().toString();
			String redisKey = AppConstants.LIMIT_IP_REQUEST + ":" + uri + ":" + ip;

			/**
			 * 设置在redis中的缓存，累加1
			 */
			long count = iCacheService.inc(redisKey, 1L);

			/**
			 * 如果该key不存在，则从0开始计算，并且当count为1的时候，设置过期时间
			 */
			if (count == 1) {
				iCacheService.expire(redisKey, limit.timeSecond(), TimeUnit.SECONDS);
			}

			/**
			 * 如果redis中的count大于限制的次数，则报错
			 */
			if (count > limit.limitCounts()) {

				log.info("用户IP[" + ip + "]访问地址[" + uri + "]超过了限定的次数[" + limit.limitCounts() + "]");

				return JsonResult.info(ResultCode.TOO_MANY_REQUESTS);
			}

			return joinPoint.proceed();
		} catch (LimitIPRequestException e) {
			throw e;
		} catch (Throwable e) {
			log.info("接口请求次数限制异常 {}", e);
			throw new Exception(e);
		}

	}

	/**
	 * 
	 * @Title: getAnnotation
	 * @Description: 获得注解
	 * @param joinPoint
	 * @return
	 * @throws Exception
	 * @author 张永胜
	 * @return LimitIPRequest
	 * @date 2018年5月10日 上午10:22:01
	 */
	private LimitIPRequest getAnnotation(JoinPoint joinPoint) throws Exception {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		if (null != methodSignature) {
			Method method = methodSignature.getMethod();
			if (null != method) {
				return method.getAnnotation(LimitIPRequest.class);
			}
		}
		return null;
	}
}