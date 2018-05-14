package com.born.facade.dto.companyDept;

import java.util.Date;

import javax.validation.constraints.Min;

import com.born.core.base.BaseValidate;
import com.born.core.page.PageBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="部门管理请求实体")
public class CompanyDeptDTO extends PageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Min(value=0)
	@ApiModelProperty(value="主键",name="id",required=true)
	private Long id;
	
	@ApiModelProperty(value="部门名称",name="deptName")
	private String deptName;//部门名称
	
	@ApiModelProperty(value="公司ID",name="companyId")
	private String companyId;//公司ID
	
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
	
	@ApiModelProperty(value="删除状态",name="IsDelete")
	private Integer IsDelete;
	
	private String ids;
	
	private Long userId;
}
