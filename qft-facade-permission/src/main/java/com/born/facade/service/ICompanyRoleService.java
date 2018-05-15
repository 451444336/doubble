package com.born.facade.service;

import java.util.Date;

import com.born.facade.dto.CompanyRoleDTO;
import com.born.facade.dto.OperateLogAuthorityDTO;
import com.born.facade.entity.CompanyRole;
import com.born.util.result.Result;

/**
 * @Description:角色管理
 * @author wangxy
 * @date 2018年4月27日 下午2:47:15
 */
public interface ICompanyRoleService {

	/**
	 * 保存角色
	 * 
	 * @param role
	 * @return
	 */
	Result insert(CompanyRoleDTO dto);

	/**
	 * 删除角色
	 * 
	 * @param id
	 */
	Result deleteById(Long id,OperateLogAuthorityDTO dto);

	/**
	 * 修改角色
	 * 
	 * @param role
	 * @return
	 */
	Result update(CompanyRoleDTO dto);

	/**
	 * 根据ID查询角色
	 * 
	 * @param id
	 * @return
	 */
	CompanyRole queryById(Long id);
	
	
	/**
	 * @Description 绑定角色菜单
	 * @author wangxy
	 * @date 2018年5月2日 上午9:59:10
	 * @param menuIds 菜单ids
	 * @param roleId 角色id
	 * @param createrId 添加人
	 * @param createTime 添加时间
	 * @return
	 */
	Result bindRoleMenu(Long[] menuIds,Long roleId,Long createrId,Date createTime);
	/**
	 * 
	* @Title: getRoleByUserId 
	* @Description: 根据用户ID 查询角色
	* @param @param id
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	Result getRoleByUserId(Long id);
	
	/**
	 * 角色列表分页查询
	 * @param pageModel
	 * @return
	 */
	Result getPageList(CompanyRoleDTO dto);

	/**
	 * 根据角色ID查询菜单
	 * 
	 * @param id
	 * @return
	 */
	Result getRoleMenus(Long roleId);
}
