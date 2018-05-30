package com.born.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseCoreEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: RoleRelation 
* @Description: 角色关系表 
* @author lijie 
* @date 2018年5月17日 上午9:13:29 
*
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name="qft_role_relation")
public class RoleRelation extends BaseCoreEntity<RoleRelation> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 公司ID
	 */
	@Column(name="company_id")
	private Long companyId;
	/**
	 * 角色ID
	 */
	@Column(name="role_id")
	private Long roleId;
}
