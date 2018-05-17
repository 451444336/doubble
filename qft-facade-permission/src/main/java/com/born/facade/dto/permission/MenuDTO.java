package com.born.facade.dto.permission;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.born.core.base.BaseValidate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
* @ClassName: PermissionInfoDTO 
* @Description: 菜单/权限详情传输DTO
* @author lijie 
* @date 2018年5月3日 上午11:09:59 
*
 */
@Data
@ApiModel(value="菜单")
public class MenuDTO extends BaseValidate<MenuDTO> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3275902221744206164L;
	/**
	 * 菜单ID
	 */
	@ApiModelProperty(value = "菜单ID", required = true)
	@NotBlank(message = "菜单ID不能为空")
	private String id;
	/**
	 * 上级ID
	 */
	@ApiModelProperty(value = "上级菜单", required = true)
	@NotBlank(message = "上级菜单ID不能为空")
	private String parentId;
	/**
	 * 是否可用：0、不可用 1、可用
	 */
	@ApiModelProperty(value = "是否可用：0、不可用 1、可用", required = true)
	private Byte isUsable;
	/**
	 * 菜单名称
	 */
	@ApiModelProperty(value = "菜单名称", required = true)
	private String menuName;
	/**
	 * 菜单访问地址
	 */
	@ApiModelProperty(value = "菜单访问地址", required = true)
	private String menuUrl;
	/**
	 * 菜单归属类型：1、pc  2、手机
	 */
	@ApiModelProperty(value = "菜单归属类型：1、pc  2、手机", required = true)
	private Byte ascription;
	/**
	 * 手机url
	 */
	@ApiModelProperty(value = "手机url")
	private String appUrl;
	/**
	 * 权限图标
	 */
	@ApiModelProperty(value = "菜单图标")
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
	private Integer menuSeq;
	/**
	 * 权限类型：1、正常菜单 2、后台菜单 3、首页菜单 4、公有菜单
	 */
	@ApiModelProperty(value = "菜单类型：1、正常菜单 2、后台菜单 3、首页菜单 4、公有菜单", required = true)
	private Byte type;
	/**
	 * 菜单级别
	 */
	@ApiModelProperty(value = "菜单级别", required = true)
	private Integer menuLevel;
}
