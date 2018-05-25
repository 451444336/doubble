package com.born.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;

@Data
@Table(name="qft_company_dept")
public class CompanyDept extends BaseEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="dept_name")
	private String deptName;//部门名称
	
	@Column(name="company_id")
	private String companyId;//公司ID
	
	@Column(name="parent_id")
	private Long parentId;//上级部门ID
	
}
