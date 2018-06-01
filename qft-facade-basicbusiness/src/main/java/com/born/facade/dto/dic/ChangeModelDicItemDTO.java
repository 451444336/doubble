package com.born.facade.dto.dic;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.born.core.base.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: ChangeModelDicItemDTO 
* @Description: 改变字典项传输数据
* @author lijie 
* @date 2018年5月31日 下午4:35:13 
*
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ChangeModelDicItemDTO extends BaseModel {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 字典code
	 */
	private String dtcode;
	/**
	 * 字典值
	 */
	private String divalue;
	/**
	 * 字典名称
	 */
	@NotBlank(message = "字典名称不能为空")
	private String diname;
	/**
	 * 排序值
	 */
	@NotNull(message = "字典排序值不能为空")
	private Integer orderNum;
	/**
	 * 公司字典（1表示是，2表示不是）
	 */
	@NotNull(message = "是否是公司字典不能为空")
	private Integer ispubDic;
	/**
	 * 字典等级
	 */
	@NotNull(message = "字典等级不能为空")
	private Integer dicRank;
}
