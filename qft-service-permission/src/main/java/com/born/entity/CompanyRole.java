package com.born.entity;


import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 角色实体类
 * @author wangxy
 * @date 2018年4月27日 下午2:15:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "qft_company_role")
public class CompanyRole extends BaseEntity<CompanyRole> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6193345295156667069L;

	/**
	 * 角色名称
	 */
	@Column(name = "role_name")
	private String roleName;
	/**
	 * 是否有效
	 */
	@Column(name = "is_valid")
	private Byte isValid;
    /**
     * 删除状态
     */
	@Column(name = "is_delete")
    private Byte IsDelete;
	/**
	 * 角色编码
	 */
	@Column(name="role_code")
	private String roleCode;
	/**
	 * 是否是超级管理员：0、否 1、是
	 */
	@Column(name="is_super_manager")
	private Byte isSuperManager;
}
