package com.born.facade.service;

import com.born.util.result.Result;

/**
 * 
* @ClassName: ISysUserService 
* @Description: 账户服务
* @author 张永胜 
* @date 2018年5月4日 下午5:52:30 
* @version 1.0
 */
public interface ISysUserService {

	/**
	 * 
	* @Title: getUser 
	* @Description: 获取用户登录信息
	* @param @param account 账号
	* @param @return
	* @author 张永胜
	* @return User
	* @date 2018年5月4日 下午5:52:47 
	* @throws
	 */
	Result getUser(String account);

}
