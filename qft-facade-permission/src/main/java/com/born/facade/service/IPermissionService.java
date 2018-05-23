package com.born.facade.service;

import java.util.List;

import com.born.core.page.PageBean;
import com.born.facade.dto.permission.AddPermissionDTO;
import com.born.facade.dto.permission.PermissionQueryDTO;
import com.born.util.result.Result;

/**
 * 
* @ClassName: IPermissionService  
* @Description: 权限相关服务  
* @author lijie
* @date 2018年4月25日  
*
 */
public interface IPermissionService {
	/**
	 * 
	* @Title: addPermission 
	* @Description: 添加权限操作
	* @param @param dto
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	Result addPermission(AddPermissionDTO dto);
	/**
	 * 
	* @Title: getCompanyInfo 
	* @Description: 根据公司url请求数据
	* @param @param corUrl
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	Result getCompanyInfoByCorUrl(String corUrl);
	/**
	 * 
	* @Title: gettCompanyInfosByPage 
	* @Description: 分页查询 demo
	* @param @param pageBean
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	Result getCompanyInfosByPage(PageBean pageBean);
	/**
	 * 
	* @Title: getPersonalPermissions 
	* @Description: 查询个人权限信息 
	* @param @param userId
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	Result getPersonalPermissions(Long userId);
	/**
	 * 
	* @Title: getPositionPermissions 
	* @Description: 查询职位权限数据 
	* @param @param positionId
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	Result getPositionPermissions(Long positionId);
	/**
	 * 
	* @Title: getPermissions 
	* @Description: 根据公司ID查询权限数据 
	* @param @param positionId
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	Result getPermissions(String companyId);
	/**
	 * 
	* @Title: addPersonalPermissions 
	* @Description: 新增个人权限数据 
	* @param @param userId
	* @param @param authorityIds
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	Result addPersonalPermissions(Long userId, List<Long> authorityIds,Long[] meunIds);
	/**
	 * 
	* @Title: getAuthorizeData 
	* @Description: 查询授权数据 
	* @param @param dto
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	Result getAuthorizeData(PermissionQueryDTO dto);
}
