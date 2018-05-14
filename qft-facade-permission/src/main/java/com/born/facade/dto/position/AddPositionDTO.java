package com.born.facade.dto.position;

import java.util.List;

import com.born.core.base.BaseValidate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
* @ClassName: AddPositionDTO 
* @Description: 职位管理请求实体
* @author 明成 
* @date 2018年5月4日 下午6:32:09 
* @version 1.0
 */
@Data
@ApiModel(value="职位管理请求实体")
public class AddPositionDTO extends BaseValidate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="职位ID",name="id")
	private Long id;
	
	@ApiModelProperty(value="职位名称",name="name")
	private String name;
	
	@ApiModelProperty(value="部门ID",name="deptId")
	private Long deptId;
	
	@ApiModelProperty(value="权限ID",name="authorityIds")
	private List<Long> authorityIds;
}
