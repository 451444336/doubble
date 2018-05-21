package com.born.facade.dto.dic;

import com.born.core.page.PageBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DicItemDTO extends PageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 父级ID
	 */
	private String pId;

}
