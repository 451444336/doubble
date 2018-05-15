package com.born.facade.dto.staff;

import com.born.core.page.PageBean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 员工DTO
 * @author 明成
 *
 */
@Data
@ApiModel(value="员工管理请求实体")
public class FindStaffListDTO extends PageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="真实姓名",name="realname")
	private String realname;
	
	@ApiModelProperty(value="工作店面",name="workstore")
	private String workstore;
	
	@ApiModelProperty(value="工作的组ID",name="grouping")
	private String grouping;
	
	@ApiModelProperty(value="城市ID",name="cityId")
	private String cityId;
	
	@ApiModelProperty(value="职务ID",name="positionId")
	private Long positionId;
	//公司ID
	private String companyId;
	//权限IDs
	private String[] permissions;
}


