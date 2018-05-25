package com.born.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.born.core.base.BaseMapper;
import com.born.entity.MenuAuthority;
import com.born.facade.vo.MenuAuthorityVO;
import com.born.facade.vo.UserAuthVO;

/**
 * 
* @ClassName: MenuAuthorityMapper 
* @Description: 菜单权限
* @author lijie 
* @date 2018年4月28日 下午3:59:11 
*
 */
@Repository
public interface MenuAuthorityMapper extends BaseMapper<MenuAuthority> {
	/**
	 * 
	* @Title: selectMenuAuthorityList 
	* @Description: 查询菜单权限
	* @param @param companyId
	* @param @return    设定文件 
	* @return List<MenuAuthorityVO>    返回类型 
	* @author lijie
	* @throws
	 */
	List<MenuAuthorityVO> selectMenuAuthorityList(String companyId);
	/**
	 * 
	* @Title: selectAuthorityListByMenuIds 
	* @Description: 根据菜单ID查询菜单操作权限数据 
	* @param @param menuIds
	* @param @return    设定文件 
	* @return List<MenuAuthorityVO>    返回类型 
	* @author lijie
	* @throws
	 */
	List<MenuAuthorityVO> selectAuthorityListByMenuIds(@Param("menuIds") List<Long> menuIds);
	/**
	 * 
	* @Title: selectAuthorityListByUserId  
	* @Description: 根据用户ID查询权限数据 
	* @param: @param userId
	* @param: @return
	* @return List<UserAuthVO>
	* @author lijie
	* @throws
	 */
	List<UserAuthVO> selectAuthorityListByUserId(Long userId);

}
