package com.born.facade.dto.permission;

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
@ApiModel(value="菜单/权限数据")
public class PermissionInfoDTO extends BaseValidate<PermissionInfoDTO> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3275902221744206164L;
	/**
	 * 
	 */
	@ApiModelProperty(value = "公司ID", required = true)
	@NotBlank(message = "公司ID不能为空")
	private String companyId;
	/**
	 * 权限ID
	 */
	@ApiModelProperty(value = "菜单/权限ID", required = true)
	@NotBlank(message = "权限ID不能为空")
	private String id;
	/**
	 * 上级ID
	 */
	@ApiModelProperty(value = "上级菜单/权限ID", required = true)
	@NotBlank(message = "上级菜单/权限ID不能为空")
	private String parentId;
	/**
	 * 是否可用：0、不可用 1、可用
	 */
	@ApiModelProperty(value = "是否可用：0、不可用 1、可用", required = true)
	private Byte isUsable;
	/**
	 * 权限名称
	 */
	@ApiModelProperty(value = "权限名称", required = true)
	private String menuName;
	/**
	 * 权限访问地址
	 */
	@ApiModelProperty(value = "权限访问地址", required = true)
	private String menuUrl;
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
	@ApiModelProperty(value = "手机排序", required = true)
	private Integer appSeq;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序", required = true)
	private Integer menuSeq;
	/**
	 * 权限类型：1、正常菜单 2、后台菜单 3、首页菜单 4、公有菜单
	 */
	@ApiModelProperty(value = "权限类型：1、正常菜单 2、后台菜单 3、首页菜单 4、公有菜单", required = true)
	private Byte type;
	/**
	 * 权限操作类型：1、查看 2、修改删除 3、操作 4、特殊
	 */
	@ApiModelProperty(value = "权限操作类型：1、查看 2、修改删除 3、操作 4、特殊", required = true)
	private Byte operType;
	/**
	 * 菜单级别
	 */
	@ApiModelProperty(value = "菜单级别", required = true)
	private Integer menuLevel;
	/**
	 * 权限编码
	 */
	@ApiModelProperty(value = "权限编码")
	private String authCode;
	
}
