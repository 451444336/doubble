package com.born.facade.dto.permission;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.born.core.base.BaseValidate;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: PermissionQueryDTO 
* @Description: 权限查询传输实体 
* @author lijie 
* @date 2018年5月10日 上午9:34:06 
*
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionQueryDTO extends BaseValidate<PermissionQueryDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1926973180348209722L;
	/**
	 * 职位ID
	 */
	@NotNull(message="职位ID不能为空")
	private Long positionId;
	/**
	 * 菜单ID
	 */
	@NotNull(message="菜单ID不能为空")
	private List<Long> menuIds;
	/**
	 * 公司ID
	 */
	@NotNull(message="公司ID不能为空")
	private Long companyId;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 操作类型：1、职位 2、个人
	 */
	@NotNull(message="操作类型不能为空")
	private Byte operType;
}
