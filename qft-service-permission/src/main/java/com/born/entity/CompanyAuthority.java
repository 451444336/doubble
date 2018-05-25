package com.born.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
/**
 * 
* @ClassName: CompanyAuthority 
* @Description: 公司权限 
* @author lijie 
* @date 2018年5月3日 下午2:32:02 
*
 */
@Data
@Table(name="qft_company_authority")
public class CompanyAuthority extends BaseEntity<CompanyAuthority> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7051556679204423855L;
	/**
	 * 公司ID
	 */
	@Column(name="company_id")
	private String companyId;
	/**
	 * 权限ID
	 */
	@Column(name="base_authority_id")
	private String baseAuthorityId;
	/**
	 * 菜单ID
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
	 * 权限类型：1、查看 2、修改删除 3、操作 4、特殊
	 */
	@Column(name="type")
	private Byte type;
	/**
	 * 是否删除：0、否 1、是
	 */
	@Column(name="is_delete")
	private Byte isDelete;
	/**
	 * 权限编码
	 */
	@Column(name="auth_code")
	private String authCode;
}
