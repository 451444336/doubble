package com.born.facade.dto.property;


import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

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
	@NotNull(message = "公司ID不能为空")
	private Long companyId;
	
	/**
	 * 区域地址
	 */
	@NotBlank(message = "区域名称不能为空")
	private String areaName;
}
