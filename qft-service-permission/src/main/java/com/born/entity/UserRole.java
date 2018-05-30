package com.born.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseCoreEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: UserRole 
* @Description: 用户角色 
* @author lijie 
* @date 2018年5月30日 下午2:19:45 
*
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name="qft_user_role")
public class UserRole extends BaseCoreEntity<UserRole> {/**
	 * 
	 */
	private static final long serialVersionUID = -6935511530426215583L;
	/**
	 * 角色ID
	 */
	@Column(name = "role_id")
	private Long roleId;
	/**
	 * 用户ID
	 */
	@Column(name = "user_id")
	private Long userId;
	/**
	 * 公司ID
	 */
	@Column(name = "company_id")
	private Long companyId;
}
