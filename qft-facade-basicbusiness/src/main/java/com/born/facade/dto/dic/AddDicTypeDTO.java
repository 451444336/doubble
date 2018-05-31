package com.born.facade.dto.dic;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: AddDicTypeDTO 
* @Description: 添加字典类型 
* @author lijie 
* @date 2018年5月31日 上午10:44:56 
*
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AddDicTypeDTO extends UpdateDicTypeDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 创建人ID
	 */
	@NotNull(message = "创建人ID不能为空")
	private Long createrId;
	/**
	 * 创建时间
	 */
	@NotNull(message = "创建时间不能为空")
	private Date createTime;
	/**
	 * 是否删除：0、否 1、是
	 */
	@NotNull(message = "删除状态不能为空")
	private Byte isDelete;
}
