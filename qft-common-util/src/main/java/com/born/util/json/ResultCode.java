package com.born.util.json;

/**
 * 枚举类
 * 
 * @ClassName: ResultCode
 * @Description: 一些状态码
 * @author 张永胜
 * @date 2018年5月8日 下午3:03:57
 * @version 1.0
 */
public enum ResultCode {
	
	/**Api key无效*/
	INVALID_API_KEY(101, "Api key无效"),
	/**无效签名 */
	INCORRECT_SIGNATURE(104, "无效签名"),
	/**请求参数过多*/
	TOO_MANY_PARAMETERS(105, "请求参数过多"),
	/** 路由回退响应提示, 请求服务出现故障*/
	ROUTE_FALLBACK(106, "请求回退响应, 服务出现故障"),
	/** 非法的请求*/
	ILLEGAL_REQUEST(107, "非法的请求"),
	/** 请求已过期*/
	OVERDUE_REQUEST(108, "请求已过期"),
	/**无效的Access Token*/
	ACCESS_TOKEN_INVALID_OR_NO_LONGER_VALID(110, "无效的Access Token"),
	/**Access Token过期*/
	ACCESS_TOKEN_EXPIRED(111, "Access Token过期"),
	/**响应成功*/
	SUCCESS(200, "响应成功"),
	/**操作失败*/
	FAIL(202, "操作失败"),
	/**请求出现错误*/
	BAD_REQUEST(400, "请求出现错误"),
	/**访问被拒绝, 未授权 */
	UNAUTHORIZED(401, "访问被拒绝, 未授权"),
	/**资源不可用*/
	FORBIDDEN(403, "服务器拒绝请求"),
	/**请求的内容不存在*/
	NOT_FOUND(404, "请求的内容不存在"),
	/**访问频繁*/
	TOO_MANY_REQUESTS(429, "访问频繁"),
	/**服务器内部错误*/
	INTERNAL_SERVER_ERROR(500, "服务器异常，请稍后重试"),
	/**服务器正忙 */
	SERVICE_UNAVAILABLE(503, "服务器正忙"),
	/**用户不存在*/
	UNKNOWN_USERNAME(1000, "用户不存在"),
	/**未知错误*/
	UNKNOWN_ERROR(1004, "未知错误"),
	/**登录错误次数过多, 请稍后再试*/
	LOGIN_ERROR_TIMES(1005, "登录错误次数过多, 请稍后再试"),
	/**无权限访问该用户数据*/
	NO_PERMISSION_TO_ACCESS_USER_DATA(1006, "无权限访问该用户数据"),
	/**验证码错误*/
	VERIFY_CODE_ERROR(1008, "验证码错误"),
	/**手机号码非法*/
	VERIFY_PHONE_ILLEGAL(1009, "非法手机号"),
	/**用户已存在*/
	VERIFY_USER_EXIST(1010, "用户已存在"),
	/**验证码过期*/
	VERIFY_CODE_EXPIRED(1013, "验证码错误或过期"),
	/**非法的请求参数*/
	INVALID_REQUEST(10000, "非法的请求参数"),
	/**用户认证失败*/
	INVALID_CLIENT(10001, "用户认证失败"),
	/**非法的授权信息*/
	INVALID_GRANT(10002, "非法的授权信息"),
	/**应用没有被授权*/
	UNAUTHORIZED_CLIENT(10003, "应用没有被授权"),
	/**参数不能包含非法字符*/
	ILLEGAL_CHARACTER(10004, "参数不能包含非法字符");

	/***
	 * 返回错误码
	 */
	public final int status_code;

	/***
	 * 返回信息描述
	 */
	public final String message;

	ResultCode(int status_code, String msg) {
		this.status_code = status_code;
		this.message = msg;
	}

}