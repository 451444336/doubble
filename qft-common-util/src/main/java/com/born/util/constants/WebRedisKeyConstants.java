package com.born.util.constants;

/**
 * 
* @ClassName: WebRedisKeyConstants 
* @Description: WEB 常量表
* @author 张永胜 
* @date 2018年5月31日 下午2:11:32 
* @version 1.0
 */
public interface WebRedisKeyConstants {

	static final String WEB = "WEB";
	
	/** 普通型-缓存命名空间 */
	static final String CACHE_NAMESPACE = "QFT:"+WEB;
	/** 保存型-缓存命名空间 */
	static final String SYSTEM_CACHE_NAMESPACE = "S:QFT:"+WEB;
	/** 锁型-缓存命名空间 */
	static final String CACHE_NAMESPACE_LOCK = "L:QFT:"+WEB;

	/** 默认设置 */
	static final String DEFAULT_SET = SYSTEM_CACHE_NAMESPACE + "DEFAULT_SET";
	/** 免租期设置 */
	static final String RENT_FREE_PERIOD = SYSTEM_CACHE_NAMESPACE + "RENT_FREE_PERIOD";
	/** 定价设置 */
	static final String FIX_PRICE = SYSTEM_CACHE_NAMESPACE + "FIX_PRICE";
}
