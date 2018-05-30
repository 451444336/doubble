package com.born.facade.dto.menu;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.born.core.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: MenuDTO 
* @Description: 菜单传输实体 
* @author lijie 
* @date 2018年5月29日 上午11:22:08 
*
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuChangeDTO extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 菜单ID
	 */
	private Long menuId;
	/**
	 * 上级菜单ID
	 */
	private Long parentId;
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
	 * 菜单地址
	 */
	@NotBlank(message = "菜单地址不能为空")
	private String menuUrl;
	/**
	 * 菜单名称
	 */
	@NotBlank(message = "菜单名称不能为空")
	private String menuName;
	/**
	 * 菜单归属类型：1、pc 2、手机
	 */
	@NotNull(message = "菜单归属类型不能为空")
	private Byte ascription;
	/**
	 * 菜单类型：1、正常菜单 2、后台菜单 3、首页菜单 4、公有菜单
	 */
	@NotNull(message = "菜单类型不能为空")
	private Byte type;
	/**
	 * 菜单编码
	 */
	@NotBlank(message = "菜单编码不能为空")
	private String menuCode;
	/**
	 * 菜单排序
	 */
	private Integer menuSeq;
}
