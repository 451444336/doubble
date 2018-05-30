package com.born.entity.configset;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @数表名称 qft_config_set
 * @开发日期 2018-05-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "qft_config_set")
public class ConfigSet extends BaseEntity<ConfigSet> {

	/** 设置类型 */
	@Column(name = "set_name")
	private String setName;

	/** 设置值 */
	@Column(name = "set_value")
	private String setValue;

	/** 描述 */
	@Column(name = "set_remark")
	private String setRemark;

	/** 租房类型：合租，整租，集中整租 */
	@Column(name = "set_type")
	private String setType;

	/** 公司ID */
	@Column(name = "company_id")
	private Long companyId;

	private static final long serialVersionUID = 1L;
}