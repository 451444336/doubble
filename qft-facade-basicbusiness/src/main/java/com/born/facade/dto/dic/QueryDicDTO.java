package com.born.facade.dto.dic;

import javax.validation.constraints.NotNull;

import com.born.core.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: QueryDicDTO 
* @Description: 查询字典DTO 
* @author lijie 
* @date 2018年5月31日 下午3:34:14 
*
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class QueryDicDTO extends BaseModel {

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
	 * 是否删除
	 */
	private Byte isDelete;
}
