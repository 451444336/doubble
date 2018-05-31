package com.born.facade.vo.dic;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
/**
 * 
* @ClassName: DicTypeBizVO 
* @Description: 字典类型数据 
* @author lijie 
* @date 2018年5月31日 下午6:42:51 
*
 */
@Data
public class DicTypeBizVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 字典菜单ID
	 */
	private Long menuDicId;
	/**
	 * 类型编码
	 */
	private String code;
	/**
	 * 字典类型名称
	 */
	private String name;
	/**
	 * 字典类型的字典数据
	 */
	private List<DicItemBizVO> items;

}
