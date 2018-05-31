package com.born.facade.dto.dic;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.born.core.base.BaseValidate;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 之后可能会扩展
 * 
 * @ClassName: UpdateDicItemDTO
 * @Description: 更新字典是DTO
 * @author 张永胜
 * @date 2018年5月18日 下午3:34:41
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class UpdateDicItemDTO extends BaseValidate<UpdateDicItemDTO> {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	/**
	 * 字典ID
	 */
	@NotNull(message = "字典ID不能为空")
	private Long id;
	/**
	 * 字典名称
	 */
	@NotBlank(message = "字典名称不能为空")
	private String name;
	/**
	 * 公司ID
	 */
	@NotNull(message = "公司ID不能为空")
	private Long companyId;
}
