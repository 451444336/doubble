package com.born.facade.dto.dic;

import com.born.core.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: QueryDicTypeDTO 
* @Description: 查询字典类型数据 
* @author lijie 
* @date 2018年6月1日 上午11:07:48 
*
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class QueryDicTypeDTO extends BaseModel {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 类型编码
	 */
	private String dtcode;
}
