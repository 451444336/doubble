package com.born.facade.service;

import java.util.List;

import com.born.util.result.Result;

/**
 * 
* @ClassName: IMenuAuthorityService 
* @Description: 菜单权限服务接口 
* @author lijie 
* @date 2018年4月28日 下午4:00:21 
*
 */
public interface IMenuAuthorityService {
	/**
	 * 
	* @Title: getMenuAuthorityList 
	* @Description: 获取所有菜单权限数据
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	Result getMenuAuthorityList(String companyId);
	/**
	 * 
	* @Title: getAuthorityListByMenuIds 
	* @Description: 根据菜单ID集合获取菜单操作权限数据 
	* @param @param menuIds
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	Result getAuthorityListByMenuIds(List<Long> menuIds);
	/**
	 * 
	* @Title: getAuthorityListByUserId  
	* @Description: 根据用户ID 查询操作权限数据 
	* @param: @param userId
	* @param: @return
	* @return Result
	* @author lijie
	* @throws
	 */
	Result getAuthorityListByUserId(Long userId);
}
