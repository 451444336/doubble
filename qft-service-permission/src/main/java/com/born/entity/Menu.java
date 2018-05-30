package com.born.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: Menu 
* @Description: 菜单数据 
* @author lijie 
* @date 2018年5月28日 下午3:36:52 
*
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "qft_menu")
public class Menu extends BaseEntity<Menu> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 上级菜单ID
	 */
	@Column(name = "parent_id")
	private Long parentId;
	/**
	 * 菜单等级
	 */
	@Column(name = "menu_level")
	private Integer menuLevel;
	/**
	 * 是否可用：0、不可用 1、可用
	 */
	@Column(name = "is_usable")
	private Byte isUsable;
	/**
	 * 菜单名称
	 */
	@Column(name = "menu_name")
	private String menuName;
	/**
	 * 菜单地址
	 */
	@Column(name = "menu_url")
	private String menuUrl;
	/**
	 * 菜单归属类型：1、pc 2、手机
	 */
	@Column(name = "ascription")
	private Byte ascription;
	/**
	 * 手机url
	 */
	@Column(name = "app_url")
	private String appUrl;
	/**
	 * 权限图标
	 */
	@Column(name = "icon")
	private String icon;
	/**
	 * 手机排序
	 */
	@Column(name = "app_seq")
	private Integer appSeq;
	/**
	 * 权限排序
	 */
	@Column(name = "menu_seq")
	private Integer menuSeq;
	/**
	 * 权限类型：1、正常菜单 2、后台菜单 3、首页菜单 4、公有菜单
	 */
	@Column(name = "type")
	private Byte type;
	/**
	 * 是否删除：0、否 1、是
	 */
	@Column(name = "is_delete")
	private Byte isDelete;
	/**
	 * 菜单编码
	 */
	@Column(name = "menu_code")
	private String menuCode;
}
