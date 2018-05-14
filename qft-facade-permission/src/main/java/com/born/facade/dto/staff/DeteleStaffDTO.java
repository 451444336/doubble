package com.born.facade.dto.staff;

import java.util.Date;

import com.born.core.base.BaseValidate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 删除员工请求参数DTO
 * @author 明成
 *
 */
@Data
@ApiModel(value="删除员工管理请求实体")
public class DeteleStaffDTO extends BaseValidate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="员工ID",name="id",required=true)
	private Long id;
	
	@ApiModelProperty(value="用户ID",name="userId",required=true)
	private Long userId;
	
	@ApiModelProperty(value="修改时间",name="updateTime")
	private Date updateTime = new Date();
	
	@ApiModelProperty(value="修改人",name="updaterId")
	private Long updaterId = 1L;
}
