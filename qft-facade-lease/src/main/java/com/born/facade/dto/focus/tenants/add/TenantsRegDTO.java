package com.born.facade.dto.focus.tenants.add;

import java.io.Serializable;

import lombok.Data;

@Data
public class TenantsRegDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 房间ID
	 */
	private long roomId;

	/**
	 * 租客基本信息
	 */
	private Tenants tenants;

	/**
	 * 租客必要信息
	 */
	private TenantsInfo tenantsInfo;

	/**
	 * 租客付费信息 这里是固定几项付费，如果扩展只写其中一项
	 */
	private OtherExpenses otherExpenses;
}
