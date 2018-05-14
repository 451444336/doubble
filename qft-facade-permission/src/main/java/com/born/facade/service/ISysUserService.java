package com.born.facade.service;

import com.born.facade.entity.User;
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
	User getUser(String account);

	/**
	 * 添加用户账户
	 * @Title: saveUser 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param user
	 * @param @return
	 * @author 张永胜
	 * @return Result
	 * @date 2018年5月4日 下午5:55:15 
	 * @throws
	 */
	Result saveUser(User user);

}
