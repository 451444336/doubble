package com.born.facade.vo.companyPosition;

import lombok.Data;
/**
 * 公司职位实体VO
 * @author 明成
 *
 */
@Data
public class CompanyPositionVO  {

	
	private Long id;
	/**
	 * 职位名称
	 */
	private String name;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 创建人
	 */
	private String createrName;
}
