package com.born.entity.expend;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "qft_focus_hosting_expend")
public class Expend extends BaseEntity<Expend> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 公司唯一ID
	 */
	@Column(name = "company_id")
	private Long companyId;

	/**
	 * 房源编号
	 */
	@Column(name = "housing_id")
	private Long housingId;

	/**
	 * 缴纳次数
	 */
	@Column(name = "pay_count")
	private Integer payCount;

	/**
	 * 交租时间
	 */
	@Column(name = "pay_date")
	private Date payDate;

	/**
	 * 有效期
	 */
	@Column(name = "valid_date")
	private Date validDate;

	/**
	 * 缴费金额
	 */
	@Column(name = "earnest_money")
	private BigDecimal earnestMoney;

	/**
	 * 支付状态 1未付 2付一半 3 已付完
	 */
	@Column(name = "pay_status")
	private Byte payStatus;

	/**
	 * 备注
	 */
	@Column(name = "remark")
	private String remark;

}
