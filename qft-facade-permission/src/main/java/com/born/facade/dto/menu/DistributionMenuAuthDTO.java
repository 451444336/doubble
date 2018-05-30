package com.born.facade.dto.menu;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.born.core.base.BaseValidate;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: DistributionMenuAuthDTO 
* @Description: 分配公司权限
* @author lijie 
* @date 2018年5月29日 下午3:05:43 
*
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DistributionMenuAuthDTO extends BaseValidate<DistributionMenuAuthDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 公司ID
	 */
	@NotNull(message = "公司ID不能为空")
	private Long companyId;
	/**
	 * 菜单ID
	 */
	@NotEmpty(message = "菜单ID不能为空")
	private List<Long> menuIds;
	/**
	 * 权限ID
	 */
	private List<Long> authIds;
	/**
	 * 操作人ID
	 */
	@NotNull(message = "操作人ID不能为空")
	private Long operatorId;

}
