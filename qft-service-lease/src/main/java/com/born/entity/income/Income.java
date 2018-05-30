package com.born.entity.income;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: Income 
* @Description: 应收房租实体类
* @author 明成 
* @date 2018年5月29日 下午2:30:57 
* @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Table(name="qft_focus_tenants_income")
public class Income extends BaseEntity<Income> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 公司唯一ID
	 */
	@Column(name="company_id")
	private Long companyId;

	/**
	 * 房源编号
	 */
	@Column(name="housing_id")
	private Long housingId;
	
	/**
	 * 房间号
	 */
	@Column(name="room_id")
	private Long roomId;
	
	/**
	 * 租客编号
	 */
	@Column(name="tenants_id")
	private Long tenantsId;
	
	/**
	 * 缴纳次数
	 */
	@Column(name="pay_count")
	private Long payCount;
	
	/**
	 * 收款时间
	 */
	@Column(name="collection_date")
	private Date collectionDate;
	
	/**
	 * 开始有效期
	 */
	@Column(name="start_valid_date")
	private Date startValidDate;
	
	/**
	 * 结束有效期
	 */
	@Column(name="end_valid_date")
	private Date endValidDate;
	
	/**
	 * 缴费金额
	 */
	@Column(name="pay_money")
	private BigDecimal payMoney;
	
	/**
	 * 物管费
	 */
	@Column(name="property_cost")
	private BigDecimal propertyCost;
	
	/**
	 * 预存费
	 */
	@Column(name="storage_cost")
	private BigDecimal storageCost;
	
	/**
	 * 服务费
	 */
	@Column(name="service_cost")
	private BigDecimal serviceCost;
	
	/**
	 * 其他费
	 */
	@Column(name="other_cost")
	private BigDecimal otherCost;
	
	/**
	 * 其他费2
	 */
	@Column(name="other_cost_second")
	private BigDecimal otherCostSecond;
	
	/**
	 * 欠款金额
	 */
	@Column(name="debt_money")
	private BigDecimal debtMoney;
	
	/**
	 * 还款时间
	 */
	@Column(name="repay_date")
	private Date repayDate;
	
	/**
	 * 收付状态 1未收 2收部分 3 已收完
	 */
	@Column(name="pay_status")
	private Byte payStatus;
	
	
	/**
	 * 实收款
	 */
	@Column(name="real_money")
	private BigDecimal realMoney;
	
	
	/**
	 * 备注金额
	 */
	@Column(name="remark")
	private String remark;
	
	/**
	 * 是否发送过催租短信  0未发  1已发 
	 */
	@Column(name="sms_type")
	private Byte smsType;
	
	/**
	 * 滞纳金
	 */
	@Column(name="late_fee")
	private BigDecimal lateFee;
}
