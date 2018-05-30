package com.born.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: AuthorityChange 
* @Description: 权限变更实体 
* @author lijie 
* @date 2018年5月4日 下午3:49:54 
*
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name="qft_authority_change")
public class AuthorityChange extends BaseEntity<AuthorityChange> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -980444417474842421L;
	/**
	 * 权限ID
	 */
	@Column(name = "authority_id")
	private Long authorityId;
	/**
	 * 模板ID
	 */
	@Column(name = "template_id")
	private Long templateId;
	/**
	 * 公司ID
	 */
	@Column(name = "company_id")
	private Long companyId;
	/**
	 * 操作类型
	 */
	@Column(name = "oper_type")
	private Byte operType;
}
