package com.born.entity.focus.tenants;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName: TenantsIndividual
 * @Description: 租客基本信息表
 * @author 张永胜
 * @date 2018年5月28日 上午11:35:40
 * @version 1.0
 */
@Data
@Table(name = "qft_focus_tenants_info")
@EqualsAndHashCode(callSuper = true)
public class TenantsInfo extends BaseEntity<TenantsInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 公司ID
	 */
	@Column(name = "company_id")
	private long companyId;

	/**
	 * 租客ID
	 */
	@Column(name = "tenant_id")
	private long tenantId;

	/**
	 * 业务员ID
	 */
	@Column(name = "salesman_id")
	private String salesmanId;

	/**
	 * 租赁开始时间
	 */
	@Column(name = "lease_starttime")
	private Date leaseStarttime;

	/**
	 * 租赁结束时间
	 */
	@Column(name = "lease_endtime")
	private Date leaseEndtime;

	/**
	 * 租赁期限年
	 */
	@Column(name = "limit_year")
	private int limitYear;

	/**
	 * 租赁期限月
	 */
	@Column(name = "limit_month")
	private int limitMonth;

	/**
	 * 租赁期限天
	 */
	@Column(name = "limit_day")
	private int limitDay;

	/**
	 * 合同性质
	 */
	@Column(name = "tenants_contract_nature")
	private String tenantsContractNature;

	/**
	 * 出房价格
	 */
	@Column(name = "lease_price")
	private BigDecimal leasePrice;

	/**
	 * 出房展示价格
	 */
	@Column(name = "lease_price_show")
	private BigDecimal leasePriceShow;

	/**
	 * 租缴费方式
	 */
	@Column(name = "payment_method")
	private String paymentMethod;

	/**
	 * 押金
	 */
	@Column(name = "deposit_money")
	private BigDecimal depositMoney;

	/**
	 * 水起数
	 */
	@Column(name = "tenant_water")
	private double tenantWater;

	/**
	 * 电起数
	 */
	@Column(name = "tenant_electricity")
	private double tenantElectricity;

	/**
	 * 气起数
	 */
	@Column(name = "tenant_gas")
	private double tenantGas;

	/**
	 * 分水表底数
	 */
	@Column(name = "tenant_part_water")
	private double tenantPartWater;

	/**
	 * 分电表底数
	 */
	@Column(name = "tenant_part_electricity")
	private double tenantPartElectricity;

	/**
	 * 分气表底数
	 */
	@Column(name = "tenant_part_gas")
	private double tenantPartGas;

	/**
	 * 递增约定
	 */
	@Column(name = "add_appoint")
	private String addAppoint;

	/**
	 * 递增金额
	 */
	@Column(name = "add_money")
	private BigDecimal addMoney;

	/**
	 * 递增方式
	 */
	@Column(name = "add_type")
	private String addType;

	/**
	 * 不规则递增ID
	 */
	@Column(name = "add_irregular_id")
	private String addIrregularId;

	/**
	 * 其他费用ID
	 */
	@Column(name = "other_expenses_id")
	private String otherExpensesId;

	/**
	 * 渠道来源
	 */
	@Column(name = "channel")
	private String channel;

	/**
	 * 入住人数
	 */
	@Column(name = "checkin_number")
	private Integer checkinNumber;

	/**
	 * 备注
	 */
	@Column(name = "tenant_note")
	private String tenantNote;

	/**
	 * 上一次租出时间 或者 托管开始时间
	 */
	@Column(name = "tenantlast_time")
	private String tenantlastTime;

}
