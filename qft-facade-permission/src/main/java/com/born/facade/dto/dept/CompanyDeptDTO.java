package com.born.facade.dto.dept;

import java.util.Date;

import com.born.core.page.PageBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value="部门管理请求实体")
@EqualsAndHashCode(callSuper = true)
public class CompanyDeptDTO extends PageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="主键",name="id",required=true)
	private Long id;
	
	@ApiModelProperty(value="部门名称",name="deptName")
	private String deptName;//部门名称
	
	@ApiModelProperty(value="公司ID",name="companyId")
	private Long companyId;//公司ID
	
	@ApiModelProperty(value="上级部门ID",name="parentId")
	private Long parentId;//上级部门ID
	
	@ApiModelProperty(value="创建时间",name="createTime")
	private Date createTime;
	
	@ApiModelProperty(value="修改时间",name="updateTime")
	private Date updateTime;
	
	@ApiModelProperty(value="创建人",name="createrId")
	private Long createrId;
	
	@ApiModelProperty(value="修改人",name="updaterId")
	private Long updaterId;
	
	private String ids;
	
	private Long userId;
}
