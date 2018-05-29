package com.born.entity.focus.tenants;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.Data;

/**
 * 
 * @ClassName: Tenants
 * @Description: 租客信息表
 * @author 张永胜
 * @date 2018年5月28日 上午11:41:17
 * @version 1.0
 */
@Data
@Table(name = "qft_focus_tenants")
public class Tenants implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	private long id;

	/**
	 * 房间ID
	 */
	@Column(name = "room_id")
	private long roomId;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 身份证ID
	 */
	@Column(name = "id_card")
	private String idCard;

	/**
	 * 电话
	 */
	private String phone;

	/**
	 * 其他电话
	 */
	@Column(name = "other_phone")
	private String otherPhone;

	/**
	 * QQ\微信
	 */
	@Column(name = "qq_wechat")
	private String qqWechat;

	/**
	 * 职业
	 */
	private String occupation;

	/**
	 * 公司地址
	 */
	@Column(name = "company_address")
	private String companyAddress;

}
