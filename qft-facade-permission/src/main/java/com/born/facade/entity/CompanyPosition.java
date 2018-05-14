package com.born.facade.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 公司职位实体
 * @author 明成
 *
 */
@Data
@Table(name="qft_company_position")
public class CompanyPosition extends BaseEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "name")
	private String name;//职位名称
	
	@Column(name = "dept_id")
	private Long deptId;//部门ID
	
}
