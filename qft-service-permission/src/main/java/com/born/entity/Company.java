package com.born.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
/**
 * 
* @ClassName: Company 
* @Description: 公司实体
* @author lijie 
* @date 2018年5月28日 下午3:33:58 
*
 */
@Data
@Table(name="qft_company")
public class Company implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 公司ID
	 */
	@Id
	private Long id;
	/**
	 * 旧版本公司ID
	 */
	@Column(name="company_id")
	private Long companyId;
	
}
