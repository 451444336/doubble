package com.born.facade.dto.position;

import java.util.Date;
import java.util.List;

import com.born.core.base.BaseValidate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
* @ClassName: AddPositionMapDTO 
* @Description: 职位管理请求实
* @author 明成 
* @date 2018年5月4日 下午4:38:12 
* @version 1.0
 */
@Data
@ApiModel(value="职位管理请求实体")
public class AddPositionMapDTO extends BaseValidate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="职位ID",name="id")
	private Long id;
	
	@ApiModelProperty(value="职位名称",name="name")
	private String name;
	
	@ApiModelProperty(value="创建时间",name="createTime")
	private Date createTime;
	
	@ApiModelProperty(value="创建人",name="createrId")
	private Long createrId;
	
	@ApiModelProperty(value="权限ID",name="authorityIds")
	private List<Long> authorityIds;
}
