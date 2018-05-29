package com.born.facade.dto.focus.tenants;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class OtherExpensesDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 预存费付费方式
	 */
	private String storageCostType;

	/**
	 * 预存费
	 */
	private BigDecimal storageCost;

	/**
	 * 物管费付费方式
	 */
	private String propertyCostType;

	/**
	 * 物管费
	 */
	private BigDecimal propertyCost;

	/**
	 * 服务费付费方式
	 */
	private String serviceCostType;

	/**
	 * 服务费
	 */
	private BigDecimal serviceCost;

	/**
	 * 其他费付费方式
	 */
	private String otherCostType;

	/**
	 * 其他费
	 */
	private BigDecimal otherCost;

}
