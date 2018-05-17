package com.born.facade.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
/**
 * 
* @ClassName: CompanyMenu 
* @Description: 菜单实体 
* @author lijie 
* @date 2018年4月28日 上午10:49:27 
*
 */
@Data
@Table(name="qft_company_menu")
public class CompanyMenu extends BaseEntity<CompanyMenu> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2952542399879354178L;
	/**
	 * 公司ID
	 */
	@Column(name = "company_id")
	private String companyId;
	/**
	 * 上级菜单ID
	 */
	@Column(name = "parent_id")
	private Long parentId;
	/**
	 * 菜单基础数据ID
	 */
	@Column(name="base_menu_id")
	private String baseMenuId;
	/**
	 * 是否可用：0、不可用 1、可用
	 */
	@Column(name="is_usable")
	private Byte isUsable;
	/**
	 * 权限名称
	 */
	@Column(name="menu_name")
	private String menuName;
	/**
	 * 权限地址
	 */
	@Column(name="menu_url")
	private String menuUrl;
	/**
	 * 权限归属类型：1、pc  2、手机
	 */
	@Column(name="ascription")
	private Byte ascription;
	/**
	 * 手机url
	 */
	@Column(name="app_url")
	private String appUrl;
	/**
	 * 权限图标
	 */
	@Column(name="icon")
	private String icon;
	/**
	 * 手机排序
	 */
	@Column(name="app_seq")
	private Integer appSeq;
	/**
	 * 权限排序
	 */
	@Column(name="menu_seq")
	private Integer menuSeq;
	/**
	 * 权限类型：1、正常菜单 2、后台菜单 3、首页菜单 4、公有菜单
	 */
	@Column(name="type")
	private Byte type;
	/**
	 * 是否删除：0、否 1、是
	 */
	@Column(name="is_delete")
	private Byte isDelete;
}
