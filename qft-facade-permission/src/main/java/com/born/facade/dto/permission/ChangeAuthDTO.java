package com.born.facade.dto.permission;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.born.core.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: ChangeAuthDTO 
* @Description: 权限传输实体 
* @author lijie 
* @date 2018年5月29日 下午5:29:22 
*
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChangeAuthDTO extends BaseModel {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 权限ID
	 */
	private Long authId;
	/**
	 * 菜单ID
	 */
	@NotNull(message = "菜单ID不能为空")
	private Long menuId;
	/**
	 * 操作人ID
	 */
	@NotNull(message = "操作人不能为空")
	private Long operId;
	/**
	 * 公司ID
	 */
	@NotNull(message = "公司ID不能为空")
	private Long companyId;
	/**
	 * 权限地址
	 */
	@NotBlank(message = "权限地址不能为空")
	private String authorityUrl;
	/**
	 * 权限名称
	 */
	@NotBlank(message = "权限名称不能为空")
	private String authorityName;
	/**
	 * 权限归属类型：1、pc  2、手机
	 */
	@NotNull(message = "权限归属类型不能为空")
	private Byte ascription;
	/**
	 * 权限类型：1、查看 2、修改删除 3、操作 4、特殊
	 */
	@NotNull(message = "权限类型不能为空")
	private Byte type;
	/**
	 * 权限编码
	 */
	@NotBlank(message = "权限编码不能为空")
	private String authCode;
	/**
	 * 权限排序
	 */
	private Integer authoritySeq;
}
