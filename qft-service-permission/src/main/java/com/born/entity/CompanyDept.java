package com.born.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: CompanyDept 
* @Description: 公司部门实体 
* @author lijie 
* @date 2018年5月28日 下午4:18:09 
*
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Table(name="qft_company_dept")
public class CompanyDept extends BaseEntity<CompanyDept> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 部门名称
	 */
	@Column(name="dept_name")
	private String deptName;
	/**
	 * 公司ID
	 */
	@Column(name="company_id")
	private Long companyId;
	/**
	 * 上级部门ID
	 */
	@Column(name="parent_id")
	private Long parentId;
	/**
	 *是否删除：0、未删除 1、删除
	 */
	@Column(name="is_delete")
	private Byte isDelete;
}
