package com.born.facade.dto.menu;

import javax.validation.constraints.NotNull;

import com.born.core.base.BaseValidate;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: MenuQueryDTO 
* @Description: 菜单查询传输实体 
* @author lijie 
* @date 2018年4月28日 下午2:14:38 
*
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuQueryDTO extends BaseValidate<MenuQueryDTO> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9077426819363975607L;
	/**
	 * 员工ID
	 */
	@NotNull(message = "员工ID不能为空")
	private Long userId;
	/**
	 * 公司ID
	 */
	@NotNull(message = "公司ID不能为空")
	private Long companyId;
	/**
	 * 菜单所属类型：1、pc 2、手机
	 */
	@NotNull(message = "菜单所属类型不能为空")
	private Byte ascription;
	/**
	 * 角色ID
	 */
	@NotNull(message = "角色ID不能为空")
	private Long roleId;
}
