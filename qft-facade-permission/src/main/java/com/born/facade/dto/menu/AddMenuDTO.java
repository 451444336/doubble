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
	 * 菜单ID
	 */
	private String menuId;
	/**
	 * 上级菜单ID
	 */
	private String parentId;
	
	/**
	 * 菜单基础数据ID
	 */
	private Long authorityBaseId;
	/**
	 * 创建人ID
	 */
	@NotNull(message="创建人ID不能为空")
	private Long createrId;
	/**
	 * 菜单级别
	 */
	@NotNull(message="菜单级别不能为空")
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
