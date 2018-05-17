package com.born.facade.dto.menu;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.born.core.base.BaseValidate;

import lombok.Data;
/**
 * 
* @ClassName: AddMenuDTO 
* @Description: 添加菜单传输数据
* @author lijie 
* @date 2018年5月3日 下午4:33:48 
*
 */
@Data
public class AddMenuDTO extends BaseValidate<AddMenuDTO> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7919453869031907624L;
	/**
	 * 公司ID
	 */
	@NotBlank(message = "公司ID不能为空")
	private String companyId;
	/**
	 * 模板ID
	 */
	private String templateId;
	/**
	 * 菜单ID
	 */
	@NotBlank(message = "菜单ID不能为空")
	private String menuId;
	/**
	 * 上级菜单ID
	 */
	private String parentId;
	/**
	 * 创建人ID
	 */
	@NotNull(message="创建人ID不能为空")
	private Long createrId;
	/**
	 * 是否可用：0、不可用 1、可用
	 */
	private Byte isUsable;
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 菜单访问地址
	 */
	private String menuUrl;
	/**
	 * 菜单归属类型：1、pc  2、手机
	 */
	private Byte ascription;
	/**
	 * 手机url
	 */
	private String appUrl;
	/**
	 * 权限图标
	 */
	private String icon;
	/**
	 * 手机排序
	 */
	private Integer appSeq;
	/**
	 * 排序
	 */
	private Integer menuSeq;
	/**
	 * 权限类型：1、正常菜单 2、后台菜单 3、首页菜单 4、公有菜单
	 */
	private Byte type;
	/**
	 * 菜单级别
	 */
	private Integer menuLevel;
	/**
	 * 下一级菜单
	 */
	private List<AddMenuDTO> nexts;
	/**
	 * 权限ID
	 */
	private List<Long> auths;
}
