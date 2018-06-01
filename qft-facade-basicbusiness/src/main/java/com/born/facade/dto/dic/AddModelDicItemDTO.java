package com.born.facade.dto.dic;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: AddModelDicItemDTO 
* @Description: 模板公司添加字典项数据
* @author lijie 
* @date 2018年5月31日 下午3:49:19 
*
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AddModelDicItemDTO extends ChangeModelDicItemDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 字典类型code
	 */
	private String dictypeCode;
	/**
	 * 父级ID
	 */
	@NotNull(message = "父级字典不能为空")
	private Long parentId;
	/**
	 * 公司唯一ID
	 */
	@NotNull(message = "公司ID不能为空")
	private Long companyId;
	/**
	 * 创建人ID
	 */
	@NotNull(message = "创建人ID不能为空")
	private Long createrId;
	
	@NotNull(message = "删除状态不能为空")
	private Byte isDelete;
	/**
	 * 是否可以删除：1、表示可删除，2、表示不可删除
	 */
	@NotNull(message = "是否可以删除不能为空")
	private Byte isPossibleDel;
	/**
	 * 创建时间
	 */
	private Date createTime;
}
