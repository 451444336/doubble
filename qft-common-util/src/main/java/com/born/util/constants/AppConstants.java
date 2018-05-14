package com.born.util.constants;

/**
 * App 常量表
 * 
 * @author zys
 *
 */
public interface AppConstants {

	/** 当前用户 */
	static final String CURRENT_USER = "CURRENT_USER";
	/** 客户端IP */
	static final String USER_IP = "USER_IP";
	/** 普通型-缓存命名空间 */
	static final String CACHE_NAMESPACE = "QFT:";
	/** 保存型-缓存命名空间 */
	static final String SYSTEM_CACHE_NAMESPACE = "S:QFT:";
	/** 锁型-缓存命名空间 */
	static final String CACHE_NAMESPACE_LOCK = "L:QFT:";
	/** TOKEN授权Key */
	static final String AUTHORIZATION = "auth";
	/** TOKEN延长期限(单位：天) */
	static final long TOKEN_DAY = 60;
	/** TOKEN延长期限(单位：小时) */
	static final long TOKEN_EXPIRES_HOUR = 1;
	/** TOKEN 键 */
	static final String TOKEN_KEY = SYSTEM_CACHE_NAMESPACE + "TOKEN_KEY";
	/** Shiro Cache */
	static final String REDIS_SHIRO_CACHE = SYSTEM_CACHE_NAMESPACE + "SHIRO_CACHE:";
	/** 错误登录次数 */
	static final String LOGIN_ERROR_COUNT = CACHE_NAMESPACE + "LOGIN_ERROR_COUNT";
	/** 缓存用户基本信息 */
	static final String USER_INFO = SYSTEM_CACHE_NAMESPACE + "USER_INFO";
	/** 缓存用户权限信息 */
	static final String USER_PERMISSION_INFO = SYSTEM_CACHE_NAMESPACE + "USER_PERMISSION_INFO";
	/** 限制访问请求 */
	static final String LIMIT_IP_REQUEST = CACHE_NAMESPACE + "LIMIT_IP_REQUEST";

}
