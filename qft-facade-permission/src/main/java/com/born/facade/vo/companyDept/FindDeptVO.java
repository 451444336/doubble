package com.born.facade.vo.companyDept;

import java.util.Date;

import lombok.Data;

/**
 * @Description: 部门vo
 * @author 明成
 * @date 2018年4月27日 下午2:15:21
 */
@Data
public class FindDeptVO{
	
	private Long id;

	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 创建人ID
	 */
	private Long createrId;

	/**
	 * 修改人ID
	 */
	private Long updaterId;
	
	private String deptName;//部门名称
	
	private String companyId;//公司ID
	
	private Long parentId;//上级部门ID
}
