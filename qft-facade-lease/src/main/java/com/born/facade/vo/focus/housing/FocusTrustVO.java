package com.born.facade.vo.focus.housing;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 
 * @description 集中整租房源托管信息实体vo
 * @author 黄伟
 * @date 2018年5月28日 上午10:32:00
 *
 */
@Data
public class FocusTrustVO{

	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 房源ID
	 */
	private Long housingId;
	/**
	 * 托管开始时间
	 */
	private String hostingCreateTime;
	/**
	 * 托管到期时间
	 */
	private String hostingEndTime;
	/**
	 * 合同期限年
	 */
	private int contractYear;
	/**
	 * 合同期限月
	 */
	private int contractMonth;
	/**
	 * 合同期限日
	 */
	private int contractDay;
	/**
	 * 递增约定
	 */
	private String addAppoint;
	/**
	 * 递增方式
	 */
	private String addType;
	/**
	 * 递增金额
	 */
	private BigDecimal addMoney;
	/**
	 * 托管价格
	 */
	private BigDecimal joePrice;
	/**
	 * 押金
	 */
	private BigDecimal depositMoney;
	/**
	 * 缴费方式
	 */
	private String paymentMethod;
	/**
	 * 免租期年
	 */
	private int vacancyYear;
	/**
	 * 免租期月
	 */
	private int vacancyMonth;
	/**
	 * 免租期天
	 */
	private int vacamcyDay;
	/**
	 * 业务员ID
	 */
	private Long salesmanId;
	/**
	 * 业务员名称
	 */
	private String salesmanName;
	/**
	 * 提前几天
	 */
	private double beforeTime;
	/**
	 * 延后几天
	 */
	private double postponeTime;

}
