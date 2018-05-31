package com.born.facade.dto.dic;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.born.core.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: ChangeDicTypeDTO 
* @Description: 字典类型变化实体
* @author lijie 
* @date 2018年5月31日 上午11:21:25 
*
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ChangeDicTypeDTO extends BaseModel {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 字典菜单主键ID
	 */
	@NotNull(message = "字典菜单主键ID不能为空")
	private Long menuDicId;
	/**
	 * 字典名称
	 */
	@NotBlank(message = "字典名称不能为空")
	private String dtname;
	/**
	 * 字典编码
	 */
	@NotBlank(message = "字典编码不能为空")
	private String dtcode;
	/**
	 * 是否公用：0 、否 1、是
	 */
	@NotNull(message = "是否公用不能为空")
	private Byte isPubdic;
	/**
	 * 拥有几级字典
	 */
	private Integer dicRank;
}
