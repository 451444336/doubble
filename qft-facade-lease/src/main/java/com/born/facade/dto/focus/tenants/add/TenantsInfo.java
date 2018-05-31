package com.born.facade.dto.focus.tenants.add;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * 
 * @ClassName: TenantsIndividual
 * @Description: 租客基本信息表
 * @author 张永胜
 * @date 2018年5月28日 上午11:35:40
 * @version 1.0
 */
@Data
public class TenantsInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 业务员ID
	 */
	private String salesmanId;

	/**
	 * 租赁开始时间
	 */
	private Date leaseStarttime;

	/**
	 * 租赁结束时间
	 */
	private Date leaseEndtime;

	/**
	 * 租赁期限年
	 */
	private int limitYear;

	/**
	 * 租赁期限月
	 */
	private int limitMonth;

	/**
	 * 租赁期限天
	 */
	private int limitDay;

	/**
	 * 出房价格
	 */
	private BigDecimal leasePrice;

	/**
	 * 租缴费方式
	 */
	private String payment_method;

	/**
	 * 押金
	 */
	private BigDecimal depositMoney;

	/**
	 * 水起数
	 */
	private double tenantWater;

	/**
	 * 电起数
	 */
	private double tenantElectricity;

	/**
	 * 气起数
	 */
	private double tenantGas;

	/**
	 * 分水表底数
	 */
	private double tenantPartWater;

	/**
	 * 分电表底数
	 */
	private double tenantPartElectricity;

	/**
	 * 分气表底数
	 */
	private double tenantPartGas;

	/**
	 * 递增约定
	 */
	private String addAppoint;

	/**
	 * 递增金额
	 */
	private BigDecimal addMoney;

	/**
	 * 递增方式
	 */
	private String addType;

	/**
	 * 备注
	 */
	private String tenantNote;

}
