package com.born.entity.code;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Table(name="qft_code_set")
public class Code extends BaseEntity<Code> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 公司唯一ID
	 */
	@Column(name="company_id")
	private String companyId;

	/**
	 * 店面ID
	 */
	@Column(name="store_id")
	private String storeId;
	
	/**
	 * 编号前缀
	 */
	@Column(name="prefix")
	private String prefix;
	
	/**
	 * 流水号位数
	 */
	@Column(name="places")
	private Long places;
	
	/**
	 * 排列方式
	 */
	@Column(name="arrange_type")
	private String arrangeType;
	
	/**
	 * 最后一次生成的
	 */
	@Column(name="new_value")
	private String newValue;
	
	/**
	 * 业务类型
	 */
	@Column(name="type")
	private String type;
}
