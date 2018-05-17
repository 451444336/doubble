package com.born.facade.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
/**
 * 
* @ClassName: MenuAuthorityBase 
* @Description: 权限/菜单基础数据
* @author lijie 
* @date 2018年5月4日 上午10:49:24 
*
 */
@Data
@Table(name="qft_menu_authority_base")
public class MenuAuthorityBase extends BaseEntity<MenuAuthorityBase> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8485899644862763628L;
	
	/**
	 * 权限ID
	 */
	@Column(name="authority_id")
	private String authorityId;
	/**
	 * 模板ID
	 */
	@Column(name="template_id")
	private String templateId;
	/**
	 * 是否可用：0、不可用 1、可用
	 */
	@Column(name="is_usable")
	private Byte isUsable;
	/**
	 * 权限名称
	 */
	@Column(name="authority_name")
	private String authorityName;
	/**
	 * 权限地址
	 */
	@Column(name="authority_url")
	private String authorityUrl;
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
	@Column(name="authority_seq")
	private Integer authoritySeq;
	/**
	 * 权限类型：1、正常菜单 2、后台菜单 3、首页菜单 4、公有菜单
	 */
	@Column(name="type")
	private Byte type;
	/**
	 * 权限操作类型：1、查看 2、修改删除 3、操作 4、特殊
	 */
	@Column(name="oper_type")
	private Byte operType;
	/**
	 * 是否删除：0、否 1、是
	 */
	@Column(name="is_delete")
	private Byte isDelete;
	/**
	 * 公司ID
	 */
	@Column(name="company_id")
	private String companyId;
	/**
	 * 权限编码
	 */
	@Column(name="auth_code")
	private String authCode;
}
