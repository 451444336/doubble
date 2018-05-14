package com.born.facade.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName: User
 * @Description: 用户表
 * @author 张永胜
 * @date 2018年5月4日 下午6:03:06
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "qft_user")
public class User extends BaseEntity<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 账号
	 */
	@Column(name = "account")
	private String account;

	/**
	 * 密码
	 */
	@Column(name = "password")
	private String password;

	/**
	 * 状态
	 */
	@Column(name = "status")
	private Byte status;

	/**
	 * 是否APP通知
	 */
	@Column(name = "is_app_notice")
	private Integer isAppNotice;

	/**
	 * 是否Web端通知
	 */
	@Column(name = "is_web_notice")
	private Integer isWebNotice;

	/**
	 * 账号来源
	 */
	@Column(name = "source_type")
	private String sourceType;

	/**
	 * 是否APP端登录
	 */
	@Column(name = "is_enable_app")
	private Integer isEnableApp;

	/**
	 * 设备号
	 */
	@Column(name = "device_number")
	private String deviceNumber;
	/**
	 * 是否删除
	 */
	@Column(name = "is_delete")
	private Byte isDelete;

}
