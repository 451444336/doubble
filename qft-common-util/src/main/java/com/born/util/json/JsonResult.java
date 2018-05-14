package com.born.util.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 统一的API 封装
 * 
 * @ClassName: JsonResult
 * @Description: APP端统一JSON封装
 * @author 张永胜
 * @date 2018年5月8日 下午3:00:26
 * @version 1.0
 */
public class JsonResult {

	protected static final Logger logger = LoggerFactory.getLogger(JsonResult.class);

	/**
	 * 
	 * @Title: getResult
	 * @Description: 统一返回结果数据
	 * @param statusCode 状态码
	 * @param message 结果信息
	 * @param data 结果数据
	 * @return
	 * @author 张永胜
	 * @return ResultEntity<T>
	 * @date 2018年5月8日 下午3:00:38
	 */
	public static <T> ResultEntity<T> getResult(int statusCode, String message, T data) {
		ResultEntity<T> result = ResultEntity.getInstance();
		result.setCode(statusCode);
		result.setMessage(message);
		result.setData(data);
		if (logger.isDebugEnabled()) {
			logger.debug("generate rest result:{}", result);
		}
		return result;
	}

	/**
	 * 
	 * @Title: success
	 * @Description: 操作成功, 返回此方法
	 * @return
	 * @author 张永胜
	 * @return ResultEntity<T>
	 * @date 2018年5月8日 下午3:01:15
	 */
	public static <T> ResultEntity<T> success() {
		return getResult(ResultCode.SUCCESS.status_code, ResultCode.SUCCESS.message, null);
	}

	/**
	 * 
	 * @Title: fail
	 * @Description: 操作失败, 返回此方法
	 * @return
	 * @author 张永胜
	 * @return ResultEntity<T>
	 * @date 2018年5月8日 下午3:01:27
	 */
	public static <T> ResultEntity<T> fail() {
		return getResult(ResultCode.FAIL.status_code, ResultCode.FAIL.message, null);
	}
	
	/**
	 * 
	 * @Title: fail
	 * @Description: 操作失败, 返回此方法
	 * @return
	 * @author 张永胜
	 * @return ResultEntity<T>
	 * @date 2018年5月8日 下午3:01:27
	 */
	public static <T> ResultEntity<T> fail(ResultCode resultCode) {
		return getResult(resultCode.status_code, resultCode.message, null);
	}

	/**
	 * 
	 * @Title: error
	 * @Description: 操作错误, 返回此方法
	 * @return
	 * @author 张永胜
	 * @return ResultEntity<T>
	 * @date 2018年5月8日 下午3:01:38
	 */
	public static <T> ResultEntity<T> error() {
		return getResult(ResultCode.UNKNOWN_ERROR.status_code, ResultCode.UNKNOWN_ERROR.message, null);
	}

	/**
	 * 
	 * @Title: error
	 * @Description: 错误JSON, 附带状态信息和数据
	 * @param message 结果信息
	 * @param data 结果数据
	 * @return
	 * @author 张永胜
	 * @return ResultEntity<T>
	 * @date 2018年5月8日 下午3:01:51
	 */
	public static <T> ResultEntity<T> error(String message, T data) {
		return getResult(ResultCode.UNKNOWN_ERROR.status_code, message, data);
	}

	/**
	 * 
	 * @Title: info
	 * @Description: 返回自定义信息
	 * @param statusCode 状态码
	 * @param message 结果信息
	 * @param data 结果数据
	 * @return
	 * @author 张永胜
	 * @return ResultEntity<T>
	 * @date 2018年5月8日 下午3:02:00
	 */
	public static <T> ResultEntity<T> info(ResultCode resultCode, String message, T data) {
		return getResult(resultCode.status_code, message, data);
	}

	/**
	 * 
	 * @Title: info
	 * @Description: 返回自定义信息
	 * @param statusCode 状态码
	 * @param data 结果数据
	 * @return
	 * @author 张永胜
	 * @return ResultEntity<T>
	 * @date 2018年5月8日 下午3:02:13
	 */
	public static <T> ResultEntity<T> info(ResultCode resultCode, T data) {
		return getResult(resultCode.status_code, resultCode.message, data);
	}

	/**
	 * 
	 * @Title: info
	 * @Description: 返回自定义信息
	 * @param statusCode 状态码
	 * @param message 结果信息
	 * @return
	 * @author 张永胜
	 * @return ResultEntity<T>
	 * @date 2018年5月8日 下午3:02:23
	 */
	public static <T> ResultEntity<T> info(ResultCode resultCode, String message) {
		return getResult(resultCode.status_code, message, null);
	}

	/**
	 * 
	 * @Title: info
	 * @Description: 指定状态码, 并返回
	 * @param statusCode 状态码
	 * @return
	 * @author 张永胜
	 * @return ResultEntity<T>
	 * @date 2018年5月8日 下午3:02:32
	 */
	public static <T> ResultEntity<T> info(ResultCode resultCode) {
		return getResult(resultCode.status_code, resultCode.message, null);
	}

}