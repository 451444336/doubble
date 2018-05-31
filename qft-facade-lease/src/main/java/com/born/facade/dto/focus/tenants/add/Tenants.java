package com.born.facade.dto.focus.tenants.add;

import java.io.Serializable;

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
public class Tenants implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 身份证ID
	 */
	private String idCard;

	/**
	 * 电话
	 */
	private String phone;

	/**
	 * 其他电话
	 */
	private String otherPhone;

	/**
	 * QQ\微信
	 */
	private String qqWechat;

	/**
	 * 职业
	 */
	private String occupation;

}
