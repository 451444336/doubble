package com.born.facade.vo.company;

import java.io.Serializable;

import lombok.Data;
/**
 * 
* @ClassName: CompanyVO 
* @Description: 公司相关返回数据
* @author lijie 
* @date 2018年5月8日 下午6:48:48 
*
 */
@Data
public class CompanyInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3979186204432710089L;
	/**
	 * 公司ID
	 */
	private Long companyId;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 公司访问URL
	 */
	private String corUrl;
}
