package com.born.entity.earnest;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Table(name="qft_focus_housing_earnest")
public class Earnest extends BaseEntity<Earnest> {

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
	 * 合同开始时间
	 */
	@Column(name="start_time")
	private Date starTime;
	
	/**
	 * 合同结束时间
	 */
	@Column(name="end_time")
	private Date endTime;
	
	/**
	 * 租赁期限年
	 */
	@Column(name="limit_year")
	private Integer limitYear;
	
	/**
	 * 租赁期限月
	 */
	@Column(name="limit_month")
	private Integer limitMonth;
	
	/**
	 * 租赁期限天
	 */
	@Column(name="limit_day")
	private Integer limitDay;
	
	/**
	 * 定金金额
	 */
	@Column(name="earnest_money")
	private BigDecimal earnestMoney;
	
	/**
	 * 定金有效期
	 */
	@Column(name="valid_time")
	private String validTime;
	
	/**
	 * 交定时间
	 */
	@Column(name="earnest_time")
	private Date earnestTime;
	
	/**
	 * 押金
	 */
	@Column(name="deposit_money")
	private BigDecimal depositMoney;
	
	/**
	 * 约定房租
	 */
	@Column(name="rent_money")
	private BigDecimal rentMoney;
	
	/**
	 * 缴费方式
	 */
	@Column(name="pay_method")
	private String pay_method;
	
	/**
	 * 定金状态 0 未签约 1 已签约 2手动取消 3 自动取消
	 */
	@Column(name="earnest_status")
	private Byte earnestStatus;
	/**
	 * 租客ID
	 */
	@Column(name="tenant_id")
	private Long tenantId;
	/**
	 * 业务员ID
	 */
	@Column(name="salesman_id")
	private Long salesmanId;
	/**
	 * 备注
	 */
	@Column(name="remark")
	private String remark;
	
	/**
	 * 姓名
	 */
	@Column(name="name")
	private String name;
	/**
	 * 身份证
	 */
	@Column(name="id_card")
	private String idCard;
	/**
	 * 联系电话
	 */
	@Column(name="phone")
	private String phone;
	
	/**
	 * 房间ID
	 */
	@Column(name="room_id")
	private Long roomId;
}
