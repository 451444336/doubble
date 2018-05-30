package com.born.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseCoreEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: CompanyAuthority 
* @Description: 公司权限 
* @author lijie 
* @date 2018年5月3日 下午2:32:02 
*
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "qft_company_authority")
public class CompanyAuthority extends BaseCoreEntity<CompanyAuthority> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7051556679204423855L;
	/**
	 * 公司ID
	 */
	@Column(name = "company_id")
	private Long companyId;
	/**
	 * 权限ID
	 */
	@Column(name = "authority_id")
	private Long authorityId;
	/**
	 * 是否删除：0、未删除 1、删除
	 */
	@Column(name = "is_delete")
	private Byte isDelete;
}
