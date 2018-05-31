package com.born.entity.focus.tenants;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @数表名称 qft_focus_tenants_check_out
 * @开发日期 2018-05-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "qft_focus_tenants_check_out")
public class TenantsCheckOut extends BaseEntity<TenantsCheckOut> {

	/** 公司ID */
	@Column(name = "company_id")
	private Long companyId;

	/** 房源ID */
	@Column(name = "housing_id")
	private Long housingId;

	/** 房间ID */
	@Column(name = "room_id")
	private Long roomId;

	/** 租客信息ID */
	@Column(name = "tenants_id")
	private Long tenantsId;

	/** 退房时间 */
	@Column(name = "check_out_time")
	private Date checkOutTime;

	/** 退房性质 */
	@Column(name = "check_out_type")
	private String checkOutType;

	/** 水止数 */
	@Column(name = "water")
	private BigDecimal water;

	/** 电止数 */
	@Column(name = "electricity")
	private BigDecimal electricity;

	/** 气止数 */
	@Column(name = "gas")
	private BigDecimal gas;

	/** 物管费缴到什么时间 */
	@Column(name = "content_fee_time")
	private Date contentFeeTime;

	/** 退还押金 (默认值为: 0.00) */
	@Column(name = "back_deposit_money")
	private BigDecimal backDepositMoney;

	/** 退还租金 (默认值为: 0.00) */
	@Column(name = "back_rents_money")
	private BigDecimal backRentsMoney;

	/** 退还预存费 (默认值为: 0.00) */
	@Column(name = "back_advance_money")
	private BigDecimal backAdvanceMoney;

	/** 退还其他 (默认值为: 0.00) */
	@Column(name = "back_other_money")
	private BigDecimal backOtherMoney;

	/** 扣除水费 */
	@Column(name = "water_money")
	private BigDecimal waterMoney;

	/** 扣除电费 */
	@Column(name = "electricity_money")
	private BigDecimal electricityMoney;

	/** 扣除气费 */
	@Column(name = "gas_money")
	private BigDecimal gasMoney;

	/** 扣除物管费 */
	@Column(name = "management_money")
	private BigDecimal managementMoney;

	/** 违约金 */
	@Column(name = "liquidated_damages")
	private BigDecimal liquidatedDamages;

	/** 垃圾费 */
	@Column(name = "waste_money")
	private BigDecimal wasteMoney;

	/** 赔偿费 */
	@Column(name = "compensation")
	private BigDecimal compensation;

	/** 清洁费 */
	@Column(name = "clean_money")
	private BigDecimal cleanMoney;

	/** 超期天数 */
	@Column(name = "exceed_Day")
	private BigDecimal exceedDay;

	/** 超期房租费 */
	@Column(name = "exceed_Money")
	private BigDecimal exceedMoney;

	/** 应退还金额 */
	@Column(name = "back_money")
	private BigDecimal backMoney;

	/** 扣除金额 */
	@Column(name = "other_money")
	private BigDecimal otherMoney;

	/** 实际退还金额 */
	@Column(name = "true_back_money")
	private BigDecimal trueBackMoney;

	/** 退房原因 */
	@Column(name = "check_out_reason")
	private String checkOutReason;

	/** 备注信息 */
	@Column(name = "check_out_remark")
	private String checkOutRemark;

	private static final long serialVersionUID = 1L;
}