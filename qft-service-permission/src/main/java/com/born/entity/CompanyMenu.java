package com.born.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: CompanyMenu 
* @Description: 菜单实体 
* @author lijie 
* @date 2018年4月28日 上午10:49:27 
*
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Table(name = "qft_company_menu")
public class CompanyMenu extends BaseEntity<CompanyMenu>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2952542399879354178L;
	/**
	 * 公司ID
	 */
	@Column(name = "company_id")
	private Long companyId;
	/**
	 * 菜单ID
	 */
	@Column(name = "menu_id")
	private Long menuId;
	/**
	 * 是否删除：0、未删除 1、删除
	 */
	@Column(name = "is_delete")
	private Byte isDelete;
}
