package com.born.facade.vo.dic;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
/**
 * 
* @ClassName: DicMenuBizVO 
* @Description: 字典菜单数据 
* @author lijie 
* @date 2018年5月31日 下午6:39:50 
*
 */
@Data
public class DicMenuBizVO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 字典菜单主键ID
	 */
	private Long id;
	/**
	 * 菜单ID
	 */
	private Long menuId;
	/**
	 * 上级菜单ID
	 */
	private Long parentId;
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 字典类型数据
	 */
	private List<DicTypeBizVO> types;
}
