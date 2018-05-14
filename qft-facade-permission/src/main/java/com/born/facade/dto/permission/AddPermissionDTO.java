package com.born.facade.dto.permission;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.born.core.base.BaseValidate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
* @ClassName: AddPermissionDTO 
* @Description: 权限添加传输DTO 
* @author lijie 
* @date 2018年5月3日 上午10:59:38 
*
 */
@Data
@ApiModel(value="公司权限添加")
public class AddPermissionDTO extends BaseValidate<AddPermissionDTO> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3022941327263875358L;
	/**
	 * 公司ID
	 */
	@ApiModelProperty(value = "公司ID", required = true)
	@NotBlank(message = "公司ID不能为空")
	private String companyId;
	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "用户ID", required = true)
	@NotBlank(message = "用户ID不能为空")
	private String userId;
	/**
	 * 模板ID
	 */
	@ApiModelProperty(value = "模板ID", required = true)
	@NotBlank(message = "模板ID不能为空")
	private String templateId;
	/**
	 * 菜单数据
	 */
	@ApiModelProperty(value = "菜单数据", required = true)
	@NotEmpty(message = "菜单数据不能为空")
	private List<PermissionInfoDTO> menus;
	
}
