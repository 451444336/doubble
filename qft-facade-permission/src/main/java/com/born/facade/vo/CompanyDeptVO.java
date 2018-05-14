package com.born.facade.vo;

import com.born.core.base.BaseVO;

import lombok.Data;

/**
 * @Description: 部门vo
 * @author 明成
 * @date 2018年4月27日 下午2:15:21
 */
@Data
public class CompanyDeptVO extends BaseVO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String deptName;//部门名称
	
	private String companyId;//公司ID
	
	private Long parentId;//上级部门ID
}
