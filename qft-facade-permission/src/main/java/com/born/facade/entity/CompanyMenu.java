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
	@Column(name="authority_base_id")
	private Long authorityBaseId;
}
