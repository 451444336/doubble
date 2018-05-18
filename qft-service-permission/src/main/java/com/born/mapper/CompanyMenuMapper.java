package com.born.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.born.core.base.BaseMapper;
import com.born.facade.dto.menu.MenuQueryDTO;
import com.born.facade.entity.CompanyMenu;
import com.born.facade.vo.MenuVO;
import com.born.facade.vo.appauth.UserRoleMenuVo;
/**
 * 
* @ClassName: CompanyMenuMapper 
* @Description: 菜单相关数据操作
* @author lijie 
* @date 2018年4月28日 上午11:46:53 
*
 */
@Repository
public interface CompanyMenuMapper extends BaseMapper<CompanyMenu> {
	/**
	 * 
	* @Title: selectMenuByCondition 
	* @Description: 根据角色查询相关的菜单数据
	* @param @param roles
	* @param @return    设定文件 
	* @return List<MenuVO>    返回类型 
	* @author lijie
	* @throws
	 */
	List<MenuVO> selectMenuByCondition(@Param("roleIds") List<Long> roles, @Param("dto") MenuQueryDTO dto);
	/**
	 * 
	* @Title: selectMenuByUserId 
	* @Description: 根据用户ID 查询菜单数据 
	* @param @param userId
	* @param @param companyId
	* @param @return    设定文件 
	* @return List<MenuVO>    返回类型 
	* @author lijie
	* @throws
	 */
	List<MenuVO> selectMenuByUserId(@Param("userId") Long userId, @Param("companyId") String companyId);
	/**
	 * 
	* @Title: selectAllMenu 
	* @Description: 查询所有菜单/公司所有
	* @param @return    设定文件 
	* @return List<MenuVO>    返回类型 
	* @author lijie
	* @throws
	 */
	List<MenuVO> selectAllMenu(@Param("companyId") String companyId,@Param("ascription") Byte ascription );
	/**
	 * 
	* @Title: selectMenuByRoleIds 
	* @Description: 根据角色ID集合查询角色信息数据
	* @param @param roleIds
	* @param @return    设定文件 
	* @return List<MenuVO>    返回类型 
	* @author lijie
	* @throws
	 */
	List<MenuVO> selectMenuByRoleIds(@Param("roleIds") List<Long> roleIds);
	

	
	/**
	 * 
	* @Title: selectMenuByRoleId 
	* @Description: 获取角色菜单
	* @param roleId 单个角色ID
	* @return 
	* @author 张永胜
	* @return Set<UserRoleMenuVo>
	* @date 2018年5月4日 下午6:17:40
	 */
	Set<UserRoleMenuVo> selectMenuByRoleId(@Param("roleId") String roleId);	

	/**
	 * 
	* @Title: selectUserMenuByRoleIds 
	* @Description: 获取角色菜单
	* @param roleIds 角色列表ID
	* @return 
	* @author 张永胜
	* @return Set<UserRoleMenuVo>
	* @date 2018年5月4日 下午6:17:53
	 */
	Set<UserRoleMenuVo> selectUserMenuByRoleIds(@Param("roleIds") List<String> roleIds);
	/**
	 * 
	* @Title: selectMenusByEntity 
	* @Description: 根据实体查询菜单数据 
	* @param @param record
	* @param @return    设定文件 
	* @return List<MenuVO>    返回类型 
	* @author lijie
	* @throws
	 */
	MenuVO selectMenuSubmenuById(@Param("menuId") Long menuId);
	
	/**
	 * 
	* @Title: updateMenuAuthorityByAuthIds 
	* @Description: 修改菜单数据是否删除状态
	* @param @param bases
	* @param @return    设定文件 
	* @return int    返回类型 
	* @author lijie
	* @throws
	 */
	int updateMenuByAuthIds(@Param("list") List<Long> list, @Param("isDelete") Byte isDelete,
			@Param("updaterId") Long updaterId);

}
