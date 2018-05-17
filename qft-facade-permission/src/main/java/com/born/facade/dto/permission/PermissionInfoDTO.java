package com.born.facade.dto.permission;

import org.hibernate.validator.constraints.NotBlank;

import com.born.core.base.BaseValidate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
* @ClassName: PermissionInfoDTO 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author lijie 
* @date 2018年5月17日 下午1:52:00 
*
 */
@Data
@ApiModel(value="菜单权限数据")
public class PermissionInfoDTO extends BaseValidate<MenuDTO> {/**
	 * 
	 */
	private static final long serialVersionUID = 4909453697343120266L;
	/**
	 * 菜单ID
	 */
	@ApiModelProperty(value = "菜单ID", required = true)
	@NotBlank(message = "菜单不能为空")
	private String menuId;
	/**
	 * 菜单操作权限ID
	 */
	@ApiModelProperty(value = "权限ID", required = true)
	@NotBlank(message = "权限ID不能为空")
	private String authId;
	/**
	 * 是否可用：0、不可用 1、可用
	 */
	@ApiModelProperty(value = "是否可用：0、不可用 1、可用", required = true)
	private Byte isUsable;
	/**
	 * 权限名称
	 */
	@ApiModelProperty(value = "权限名称", required = true)
	private String authName;
	/**
	 * 权限访问地址
	 */
	@ApiModelProperty(value = "权限访问地址", required = true)
	private String authUrl;
	/**
	 * 权限归属类型：1、pc  2、手机
	 */
	@ApiModelProperty(value = "权限归属类型：1、pc  2、手机", required = true)
	private Byte ascription;
	/**
	 * 手机url
	 */
	@ApiModelProperty(value = "手机url")
	private String appUrl;
	/**
	 * 权限图标
	 */
	@ApiModelProperty(value = "权限图标")
	private String icon;
	/**
	 * 手机排序
	 */
	@ApiModelProperty(value = "手机排序")
	private Integer appSeq;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer authSeq;
	/**
	 * 权限操作类型：1、查看 2、修改删除 3、操作 4、特殊
	 */
	@ApiModelProperty(value = "权限操作类型：1、查看 2、修改删除 3、操作 4、特殊", required = true)
	private Byte type;
	/**
	 * 权限编码
	 */
	@ApiModelProperty(value = "权限编码")
	private String authCode;
}
