package com.born.facade.dto.property;


import com.born.core.page.PageBean;

import lombok.Data;

@Data
public class ProvCityAreaDTO extends PageBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8367935560354585418L;

	/** 区域主键ID */
	private Long id;
	/** 区域名称 */
    private String areaName;
}
