package com.born.entity.focus.housing;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @description 集中整租房源托管信息实体类
 * @author 黄伟
 * @date 2018年5月28日 上午10:20:41
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "qft_focus_trust")
public class FocusTrust extends BaseEntity<FocusTrust>{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -531109986699734296L;
	/**
	 * 公司ID
	 */
	@Column(name = "company_id")
	private Long companyId;
	/**
	 * 托管开始时间
	 */
	@Column(name = "hosting_create_time")
	private Date hostingCreateTime;
	/**
	 * 托管到期时间
	 */
	@Column(name = "hosting_end_time")
	private Date hostingEndTime;
	/**
	 * 房源ID
	 */
	@Column(name = "housing_id")
	private Long housingId;
	/**
	 * 合同期限年
	 */
	@Column(name = "contract_year")
	private int contractYear;
	/**
	 * 合同期限月
	 */
	@Column(name = "contract_month")
	private int contractMonth;
	/**
	 * 合同期限日
	 */
	@Column(name = "contract_day")
	private int contractDay;
	/**
	 * 递增约定
	 */
	@Column(name = "add_appoint")
	private byte addAppoint;
	/**
	 * 递增方式
	 */
	@Column(name = "add_type")
	private byte addType;
	/**
	 * 递增金额
	 */
	@Column(name = "add_money")
	private BigDecimal addMoney;
	/**
	 * 不规则递增ID
	 */
	@Column(name = "add_irregular_id")
	private Long addIrregularId;
	/**
	 * 托管价格
	 */
	@Column(name = "joe_price")
	private BigDecimal joePrice;
	/**
	 * 押金
	 */
	@Column(name = "deposit_money")
	private BigDecimal depositMoney;
	/**
	 * 缴费方式
	 */
	@Column(name = "payment_method")
	private String paymentMethod;
	/**
	 * 免租期年
	 */
	@Column(name = "vacancy_year")
	private int vacancy_year;
	/**
	 * 免租期月
	 */
	@Column(name = "vacancy_month")
	private int vacancyMonth;
	/**
	 * 免租期天
	 */
	@Column(name = "vacamcy_day")
	private int vacamcyDay;
	/**
	 * 业务员ID
	 */
	@Column(name = "salesman_id")
	private Long salesmanId;
	/**
	 * 提前几天
	 */
	@Column(name = "before_time")
	private double beforeTime;
	/**
	 * 延后几天
	 */
	@Column(name = "postpone_time")
	private double postponeTime;
	
}
