package com.born.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 公司职位实体
 * @author 明成
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Table(name="qft_company_position")
public class CompanyPosition extends BaseEntity<CompanyPosition> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 职位名称
	 */
	@Column(name = "name")
	private String name;
	/**
	 * 部门ID
	 */
	@Column(name = "dept_id")
	private Long deptId;
	/**
	 * 是否删除:0、否1、是
	 */
	@Column(name="is_delete")
	private Byte isDelete;
}
