package com.born.facade.vo.dic;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
/**
 * 
* @ClassName: DicItemBizVO 
* @Description: 字典数据项 
* @author lijie 
* @date 2018年5月31日 下午6:47:23 
*
 */
@Data
public class DicItemBizVO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 名称
	 */
	private String itemName;
	/**
	 * 上级ID
	 */
	private Long parentId;
	/**
	 * 下一级
	 */
	private List<DicItemBizVO> nexts;
}
