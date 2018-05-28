package com.born.util;

import com.born.core.rediscache.ICacheService;
import com.born.facade.vo.UserApiVO;
import com.born.util.String.StringUtil;
import com.born.util.constants.AppConstants;

/**
 * 
 * @ClassName: AppUtil
 * @Description: App层辅助类
 * @author 张永胜
 * @date 2018年5月4日 下午6:40:55
 * @version 1.0
 */
public class AppUtil {

	/**
	 * 保存当前用户
	 * 
	 * @param iCacheService
	 *            缓存服务对象
	 * @param v
	 *            保存值
	 */
	public static final void saveCurrentUser(ICacheService<String, Object> iCacheService, Object v) {
		UserApiVO user = (UserApiVO) v;
		iCacheService.set(StringUtil.appendRedisKey(AppConstants.CURRENT_USER, user.getId()), user);
	}

	/**
	 * 获取当前用户
	 * 
	 * @param iCacheService
	 *            缓存服务对象
	 * @param key
	 *            键值
	 * @return
	 */
	public static final Object getCurrentUser(ICacheService<String, UserApiVO> iCacheService, String key) {
		Object obj = iCacheService.get(StringUtil.appendRedisKey(AppConstants.CURRENT_USER, key));
		if (obj != null) {
			UserApiVO user = (UserApiVO) obj;
			return user;
		}
		return null;
	}

	/**
	 * 移除当前用户账户
	 * 
	 * @param iCacheService
	 *            缓存服务
	 * @param key
	 *            键值
	 */
	public static final void removeCurrentUser(ICacheService<String, Object> iCacheService, String key) {
		iCacheService.remove(key);
	}

}
