package com.born.facade.dto.dic;

import java.io.Serializable;

import lombok.Data;

@Data
public class UpdateDicItemSortDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	private String id;
	
	/**
	 * 排序值
	 */
	private int sort;
}
