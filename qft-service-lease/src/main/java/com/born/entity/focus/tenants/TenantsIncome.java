package com.born.entity.focus.tenants;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Table(name = "qft_focus_tenants_income")
@EqualsAndHashCode(callSuper = true)
public class TenantsIncome extends BaseEntity<TenantsIncome> {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	/**
	 * 公司ID
	 */
	@Column(name = "company_id")
	private String companyId;

	/**
	 * 房源编号
	 */
	@Column(name = "housing_id")
	private long housing_id;

	/**
	 * 房间号
	 */
	@Column(name = "room_id")
	private long roomId;

	/**
	 * 租客编号
	 */
	@Column(name = "tenantsId")
	private long tenantsId;

	/**
	 * 缴纳次数
	 */
	@Column(name = "pay_count")
	private int payCount;

	/**
	 * 收款时间
	 */
	@Column(name = "collection_date")
	private Date collectionDate;

	/**
	 * 有效期
	 */
	@Column(name = "valid_date")
	private Date validDate;

	/**
	 * 缴费金额
	 */
	@Column(name = "pay_money")
	private BigDecimal payMoney;

	/**
	 * 物管费
	 */
	@Column(name = "property_cost")
	private BigDecimal propertyCost;

	/**
	 * 预存费
	 */
	@Column(name = "storage_cost")
	private BigDecimal storageCost;

	/**
	 * 服务费
	 */
	@Column(name = "service_cost")
	private BigDecimal serviceCost;

	/**
	 * 其他费
	 */
	@Column(name = "other_cost")
	private BigDecimal otherCost;

	/**
	 * 其他费2
	 */
	@Column(name = "other_cost_second")
	private BigDecimal otherCostSecond;

	/**
	 * 欠款金额
	 */
	@Column(name = "debt_money")
	private BigDecimal debtMoney;

	/**
	 * 还款时间
	 */
	@Column(name = "repay_date")
	private BigDecimal repayDate;

	/**
	 * 收付状态 1未收 2收部分 3 已收完
	 */
	@Column(name = "pay_status")
	private byte payStatus;

	/**
	 * 实收款
	 */
	@Column(name = "real_money")
	private BigDecimal realMoney;

	/**
	 * 备注
	 */
	@Column(name = "remark")
	private String remark;

	/**
	 * 是否发送过催租短信 0未发 1已发
	 */
	@Column(name = "sms_type")
	private String smsType;

	/**
	 * 滞纳金
	 */
	@Column(name = "late_fee")
	private BigDecimal lateFee;

}
