package com.born.facade.service;

import com.born.util.result.Result;

/**
 * 
 * @ClassName: ICompanyRoleAuthService
 * @Description: 角色权限服务
 * @author 张永胜
 * @date 2018年5月4日 下午6:00:11
 * @version 1.0
 */
public interface ICompanyRoleAuthService {

	/**
	 * 
	 * @Title: findRoleAuthList
	 * @Description: 查询角色菜单权限列表数据
	 * @param userId
	 *            用户ID
	 * @return
	 * @author 张永胜
	 * @return Result
	 * @date 2018年5月4日 下午6:02:00
	 */
	Result findRoleAuthList(String userId);
}
