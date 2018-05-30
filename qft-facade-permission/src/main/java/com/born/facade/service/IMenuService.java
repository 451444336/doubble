package com.born.facade.service;

import java.util.List;

import com.born.core.base.BaseModel;
import com.born.core.base.IBaseService;
import com.born.core.page.PageBean;
import com.born.facade.dto.menu.DistributionMenuAuthDTO;
import com.born.facade.dto.menu.MenuChangeDTO;
import com.born.facade.dto.menu.MenuQueryDTO;
import com.born.util.result.Result;

/**
 * 
* @ClassName: ICompanyMenuService 
* @Description: 菜单服务接口
* @author lijie 
* @date 2018年4月28日 上午11:48:37 
*
 */
public interface IMenuService extends IBaseService<BaseModel>{

	/**
	 * 
	* @Title: getMenuTreeByUserId 
	* @Description: 根据用户id查询公司下面的菜单列表树型结构
	* @param @param dto
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
    Result getMenuTreeByUserId(MenuQueryDTO dto);
   /**
    * 
   * @Title: getMenuTreeByCompanyId 
   * @Description: 根据公司id查询公司下面的菜单列表树型结构
   * @param @param companyId
   * @param @param ascription
   * @param @return    设定文件 
   * @return Result    返回类型 
   * @author lijie
   * @throws
    */
	Result getMenuTreeByCompanyId(Long companyId, Byte ascription);
    /**
     * 
    * @Title: getMenuByRoleIds 
    * @Description: 根据角色ID 查询菜单 
    * @param @param roleIds
    * @param @return    设定文件 
    * @return Result    返回类型 
    * @author lijie
    * @throws
     */
	Result getMenuByRoleIds(List<Long> roleIds, Long companyId);
    /**
     * 
    * @Title: addMenu 
    * @Description: 添加菜单数据
    * @param @param dto
    * @param @return    设定文件 
    * @return Result    返回类型 
    * @author lijie
    * @throws
     */
    Result addMenu(MenuChangeDTO dto);
    /**
     * 
    * @Title: addCompanyMenuAuth 
    * @Description: 添加公司菜单权限数据 
    * @param @param dto
    * @param @return    设定文件 
    * @return Result    返回类型 
    * @author lijie
    * @throws
     */
    Result addCompanyMenuAuth(DistributionMenuAuthDTO dto);
	/**
	 * 
	* @Title: getSubmenuMenu 
	* @Description: 根据菜单ID查询子菜单数据/不存在则返回自己详情数据
	* @param @param menuId
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
    Result getSubmenuMenuById(Long menuId);
    /**
     * 
    * @Title: getMenuListByUserId 
    * @Description: 根据用户ID查询菜单数据
    * @param @param companyId
    * @param @param userId
    * @param @return    设定文件 
    * @return Result    返回类型 
    * @author lijie
    * @throws
     */
	Result getMenuListByUserId(Long companyId, Long userId);
	/**
	 * 
	* @Title: getMenuList 
	* @Description: 查询公司所有菜单
	* @param @param companyId
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	Result getMenuList(Long companyId);
	/**
	 * 
	* @Title: getMenuListByPage 
	* @Description: 分页查询菜单数据
	* @param @param pangeBean
	* @param @param type
	* @param @param companyId
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	Result getMenuListByPage(PageBean pangeBean, Byte type, Long companyId);
}
