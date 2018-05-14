package com.born.facade.entity;


import java.util.Date;

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
	 * 是否能权限编辑
	 */
	@Column(name = "is_auth_edit")
	private Integer isAuthEdit;
	/**
	 * 是否有效
	 */
	@Column(name = "is_valid")
	private Integer isValid;
	/**
	 * 公司ID
	 */
	@Column(name = "company_id")
	private String companyId;
	/**
     * 创建时间
     */
	@Column(name = "create_time")
    private Date createTime;
    /**
     * 修改时间
     */
	@Column(name = "update_time")
    private Date updateTime;
    /**
     * 创建人ID
     */
	@Column(name = "creater_id")
    private Long createrId;
    /**
     * 修改人ID
     */
	@Column(name = "updater_id")
    private Long updaterId;
    /**
     * 删除状态
     */
	@Column(name = "is_delete")
    private Integer IsDelete;
}
