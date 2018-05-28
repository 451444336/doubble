package com.born.facade.vo;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserApiVO extends BaseEntity<UserApiVO> {

	private static final long serialVersionUID = 1L;

	/**
	 * 账号
	 */
	private String account;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 状态
	 */
	private Byte status;

	/**
	 * 是否APP通知
	 */
	private Integer isAppNotice;

	/**
	 * 是否Web端通知
	 */
	private Integer isWebNotice;

	/**
	 * 是否APP端登录
	 */
	private Integer isEnableApp;

	/**
	 * 系统锁定
	 */
	private Byte sysStatus;
}
