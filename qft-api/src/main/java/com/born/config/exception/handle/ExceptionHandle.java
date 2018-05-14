package com.born.config.exception.handle;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.born.config.exception.UnauthorizedException;
import com.born.core.exception.BizException;
import com.born.util.json.JsonResult;
import com.born.util.json.ResultCode;
import com.born.util.json.ResultEntity;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @ClassName: ExceptionHandle
 * @Description: 该处理类主要是捕获Controller层出现的异常 其他层出现的异常并不会捕获到
 * @author 张永胜
 * @date 2018年5月10日 上午10:29:57
 * @version 1.0
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandle {

	/**
	 * 捕捉UnauthorizedException
	 * 
	 * @return
	 */
	@ExceptionHandler(UnauthorizedException.class)
	public ResultEntity<String> handle401() {
		return JsonResult.info(ResultCode.UNAUTHORIZED);
	}

	/**
	 * 捕捉IllegalArgumentException 主要是一些参数
	 * 
	 * @return
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResultEntity<String> illegalArgument() {
		return JsonResult.info(ResultCode.INVALID_REQUEST);
	}

	/**
	 * biz业务异常处理
	 * 
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = BizException.class)
	@ResponseBody
	public ResultEntity<String> bizJsonExceptionHandler(BizException e) {
		log.error("数据服务端接口异常 >> ", e);
		return JsonResult.info(ResultCode.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 
	 * @Title: exceptionHandler
	 * @Description: 普通异常
	 * @param e
	 * @return
	 * @author 张永胜
	 * @return ResultEntity<String>
	 * @date 2018年5月10日 上午10:35:28
	 */
	@ExceptionHandler(value = Throwable.class)
	@ResponseBody
	public ResultEntity<String> exceptionHandler(Throwable e) {
		log.error("App接口异常 >> ", e);
		return JsonResult.info(ResultCode.INTERNAL_SERVER_ERROR);
	}
}
