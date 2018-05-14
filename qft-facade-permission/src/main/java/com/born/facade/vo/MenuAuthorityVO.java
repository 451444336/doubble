package com.born.facade.vo;

import java.io.Serializable;

import lombok.Data;
/**
 * 
* @ClassName: MenuAuthorityVO 
* @Description: 菜单权限返回实体 
* @author lijie 
* @date 2018年4月28日 下午4:17:08 
*
 */
@Data
public class MenuAuthorityVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 权限ID
	 */
	private Long authorityId;
	/**
	 * 菜单ID
	 */
	private Long menuId;
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 公司ID
	 */
	private String companyId;
	/**
	 * 权限名称
	 */
	private String authorityName;
	/**
	 * 权限地址
	 */
	private String authorityUrl;
	/**
	 * 权限归属类型：1、pc  2、手机
	 */
	private Byte ascription;
	/**
	 * 权限图标
	 */
	private String icon;
	/**
	 * 权限操作类型：1、查看 2、修改删除 3、操作 4、特殊
	 */
	private Byte operType;
	/**
	 * 是否可用：0、不可用 1、可用
	 */
	private Byte isUsable;
}
