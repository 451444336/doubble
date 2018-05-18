package com.born.service.impl.extend;

import java.util.List;

import com.born.facade.dto.permission.PermissionQueryDTO;
import com.born.facade.vo.permission.MenuPermissionVO;

/**
 * 
* @ClassName: MenuPermissionFactory 
* @Description: 菜单权限生成器
* @author lijie 
* @date 2018年5月18日 下午2:05:47 
*
 */
public interface MenuPermissionContent {
	/**
	 * 
	* @Title: genMenuAuth 
	* @Description: 生成菜单权限数据
	* @param @param dto
	* @param @return    设定文件 
	* @return List<MenuPermissionVO>    返回类型 
	* @author lijie
	* @throws
	 */
	List<MenuPermissionVO> genMenuAuth(PermissionQueryDTO dto) throws CloneNotSupportedException;
	
}
