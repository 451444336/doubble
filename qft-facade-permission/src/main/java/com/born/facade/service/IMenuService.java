package com.born.facade.service;

import java.util.List;

import com.born.facade.dto.menu.AddMenuDTO;
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
public interface IMenuService {

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
	Result getMenuTreeByCompanyId(String companyId, Byte ascription);
   /**
    * 
   * @Title: getMenuList 
   * @Description: 获取所有/公司 菜单(备注：如果公司ID 为空则查询所有菜单，如果不为空则查询公司下面的菜单)
   * @param @param companyId
   * @param @return    设定文件 
   * @return Result    返回类型 
   * @author lijie
   * @throws
    */
    Result getMenuList(String companyId);
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
    Result getMenuByRoleIds(List<Long> roleIds);
    /**
     * 
    * @Title: addMenu 
    * @Description: 添加菜单
    * @param @param list
    * @param @return    设定文件 
    * @return Result    返回类型 
    * @author lijie
    * @throws
     */
    Result addMenu(List<AddMenuDTO> list);
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
	Result getMenuListByUserId(String companyId, Long userId);
}
