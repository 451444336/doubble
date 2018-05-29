package com.born.facade.dto.focus.tenants;

import java.io.Serializable;

import lombok.Data;

@Data
public class FocusRegDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 房间ID
	 */
	private long roomId;

	/**
	 * 公司ID
	 */
	private long companyId;

	/**
	 * 租客基本信息
	 */
	private TenantsDTO tenants;

	/**
	 * 租客必要信息
	 */
	private TenantsInfoDTO tenantsInfo;

	/**
	 * 租客付费信息 这里是固定几项付费，如果扩展只写其中一项
	 */
	private OtherExpensesDTO otherExpenses;
}
