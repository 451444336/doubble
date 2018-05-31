package com.born.facade.dto.dic;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: UpdateModelDicItemDTO 
* @Description: 修改模板公司字典传输实体 
* @author lijie 
* @date 2018年5月31日 下午4:38:27 
*
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateModelDicItemDTO extends ChangeModelDicItemDTO {

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
