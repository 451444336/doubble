package com.born.facade.dto.property;


import com.born.core.page.PageBean;

import lombok.Data;

/**
 * 
 * @description 楼盘地址dto
 * @author 黄伟
 * @date 2018年5月31日 下午2:58:19
 */
@Data
public class AreaPropertyDTO extends PageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 90697457189598516L;

	/**
	 * 公司ID
	 */
	private Long companyId;
	
	/**
	 * 区域地址
	 */
	private String areaName;
}
