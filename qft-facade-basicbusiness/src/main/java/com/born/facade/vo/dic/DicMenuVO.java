package com.born.facade.vo.dic;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * 
 * @ClassName: DicMenu
 * @Description: 菜单字典
 * @author 张永胜
 * @date 2018年5月17日 下午8:03:35
 * @version 1.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DicMenuVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	private String id;

	/**
	 * 父级ID
	 */
	private String pId;

	/**
	 * 菜单名称
	 */
	private String name;

	/**
	 * 节点菜单
	 */
	private Set<DicMenuVO> subMenu;

	/**
	 * 最后节点字典类型
	 */
	private Set<DicTypeVO> subType;
}
