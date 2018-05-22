package com.born.facade.vo.dic;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class DicItemSortBoxVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 数据条数
	 */
	private long count;

	/**
	 * 数据结果级
	 */
	private List<DicItemSortVO> itemlist;
}
