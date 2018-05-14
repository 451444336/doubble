package com.born.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.born.core.base.BaseMapper;
import com.born.facade.dto.CompanyRoleDTO;
import com.born.facade.entity.CompanyRole;
import com.born.facade.vo.CompanyRoleVO;
import com.born.facade.vo.RoleMenuVO;
import com.born.facade.vo.RoleVO;

/**
 * @Description: 角色Mapper
 * @author wangxy
 * @date 2018年4月27日 下午3:21:19
 */
@Repository
public interface CompanyRoleMapper extends BaseMapper<CompanyRole> {

	/**
	 * 
	 * @Title: findRoleByUserId
	 * @Description: 获取用户角色列表
	 * @param userId
	 *            用户ID
	 * @return
	 * @author 张永胜
	 * @return List<CompanyRole>
	 * @date 2018年5月4日 下午6:14:45
	 */
	List<CompanyRole> selectRoleByUserId(String userId);

	/**
	 * 
	 * @Title: selectRoleListByUserId
	 * @Description: 根据用户ID查询角色
	 * @param @param
	 *            userId
	 * @param @return
	 *            设定文件
	 * @return List<CompanyRoleVO> 返回类型
	 * @author lijie
	 */
	List<RoleVO> selectRoleListByUserId(@Param("userId") Long userId);

	/**
	 * 删除角色菜单
	 * 
	 * @param roleId
	 *            角色id
	 */
	void deleteRoleMenuByRoleId(@Param("roleId") Long roleId);

	/**
	 * 批量插入角色菜单
	 * 
	 * @param param
	 */
	void insertRoleMenuBatch(@Param("map") Map<String, Object> map);

	/**
	 * 角色列表分页查询
	 * 
	 * @param dto
	 * @return
	 */
	List<CompanyRoleVO> selectCompanyRoleList(CompanyRoleDTO dto);
	
	/**
	 * 根据角色ID删除角色
	 * @param roleId
	 */
	void deleteRoleByRoleId(@Param("id") Long id);
	
	/**
	 * 根据角色ID查询菜单
	 * @param roleId 角色ID
	 * @return
	 */
	List<RoleMenuVO> selectRoleMenus(@Param("roleId") Long roleId);
}
