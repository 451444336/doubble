package com.born.facade.dto;

import java.util.Date;

import javax.validation.constraints.Min;

import com.born.core.page.PageBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 公司职位表
 * @author 明成
 *
 */
@Data
@ApiModel(value="职位管理请求实体")
public class CompanyPositionDTO extends PageBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Min(value=0)
	@ApiModelProperty(value="主键",name="id",required=true)
	private Long id;
	
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
	
	@ApiModelProperty(value="职位名称",name="name")
	private String name;
	
	@ApiModelProperty(value="部门ID",name="deptId")
	private Long deptId;
}
