package com.born.facade.dto.dic;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: UpdateDicTypeDTO 
* @Description: 修改字典类型 
* @author lijie 
* @date 2018年5月31日 上午11:12:04 
*
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateDicTypeDTO extends ChangeDicTypeDTO {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	@NotNull(message = "主键ID不能为空")
	private Long id;
	/**
	 * 修改人ID
	 */
	@NotNull(message = "修改人ID不能为空")
	private Long updaterId;
	/**
	 * 修改时间
	 */
	@NotNull(message = "修改时间不能为空")
	private Date updateTime;
}
