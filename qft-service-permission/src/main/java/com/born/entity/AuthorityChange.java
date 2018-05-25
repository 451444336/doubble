package com.born.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
/**
 * 
* @ClassName: AuthorityChange 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author lijie 
* @date 2018年5月4日 下午3:49:54 
*
 */
@Data
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
	private String authorityId;
	/**
	 * 模板ID
	 */
	@Column(name = "template_id")
	private String templateId;
	/**
	 * 公司ID
	 */
	@Column(name = "company_id")
	private String companyId;
	/**
	 * 操作类型
	 */
	@Column(name = "oper_type")
	private Byte operType;
	/**
	 * 老数据表的用户ID
	 */
	@Column(name = "old_user_id")
	private String oldUserId;
}
