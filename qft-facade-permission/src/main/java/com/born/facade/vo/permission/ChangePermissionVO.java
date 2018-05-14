package com.born.facade.vo.permission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.born.facade.vo.MenuVO;
import com.born.facade.vo.permission.MenuPermissionVO;

import lombok.Data;
/**
 * 
* @ClassName: PermissionVO 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author lijie 
* @date 2018年5月9日 下午8:15:05 
*
 */
@Data
public class ChangePermissionVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1207969887066882414L;
	/**
	 * 菜单数据
	 */
	private List<MenuVO> menus = new ArrayList<>(1);
	/**
	 * 权限数据
	 */
	private List<MenuPermissionVO> permissions = new ArrayList<>(1);
}
