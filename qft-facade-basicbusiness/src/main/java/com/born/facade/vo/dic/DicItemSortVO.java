package com.born.facade.vo.dic;

import java.io.Serializable;

import lombok.Data;

@Data
public class DicItemSortVO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	private String id;

	/**
	 * 字典名称
	 */
	private String name;

	/**
	 * 排序值
	 */
	private String sort;

	/**
	 * 总数,这里只能每个设置总数了
	 */
	private long count;

}
