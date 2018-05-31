package com.born.facade.dto.dic;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.born.core.base.BaseValidate;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: AddDicItemDTO 
* @Description: 添加字典项 
* @author lijie 
* @date 2018年5月31日 下午2:37:22 
*
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class AddDicItemDTO extends BaseValidate<AddDicItemDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 上级字典ID
	 */
	@NotBlank(message = "上级字典ID不能为空")
	private String pId;
	/**
	 * 二级名称数据,多个参数以逗号隔开
	 */
	@NotBlank(message = "二级名称数据不能为空")
	private String dicItem;
	/**
	 * 公司ID
	 */
	@NotNull(message = "公司ID不能为空")
	private Long companyId;
	/**
	 * 创建人ID
	 */
	@NotNull(message = "创建人ID不能为空")
	private Long ctraterId;
	/**
	 * 字典等级
	 */
	@NotNull(message = "字典等级不能为空")
	private Integer dicRank;
}
