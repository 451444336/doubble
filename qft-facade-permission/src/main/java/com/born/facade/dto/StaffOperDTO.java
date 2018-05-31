package com.born.facade.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.born.core.base.BaseValidate;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: StaffOperDTO 
* @Description: 用户操作传输实体 
* @author lijie 
* @date 2018年5月7日 下午2:26:14 
*
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StaffOperDTO extends BaseValidate<StaffOperDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8825922363722168244L;
	/**
	 * 用户ID
	 */
	@NotNull(message="用户ID不能为空")
	private Long userId;
	/**
	 * 登录IP
	 */
	@NotBlank(message="登录IP不能为空")
	private String lastIp;
}
