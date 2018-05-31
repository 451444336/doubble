package com.born.facade.dto.dic;

import javax.validation.constraints.NotNull;

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
	/**
	 * 公司ID
	 */
	@NotNull(message = "公司ID不能为空")
	private Long companyId;

}
