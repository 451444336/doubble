package com.born.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseCoreEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: CompanyRoleMenu 
* @Description: 角色菜单 
* @author lijie 
* @date 2018年5月9日 下午4:48:16 
*
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "qft_company_role_menu")
public class CompanyRoleMenu extends BaseCoreEntity<CompanyRoleMenu> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4358229381631467860L;
	/**
	 * 菜单ID
	 */
	@Column(name = "menu_id")
	private Long menuId;
	/**
	 * 角色ID
	 */
	@Column(name = "role_id")
	private Long roleId;
	/**
	 * 公司ID
	 */
	@Column(name = "company_id")
	private Long companyId;
}
