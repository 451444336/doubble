package com.born.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 公司员工实体
 * 
 * @author 明成
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "qft_user_info")
public class CompanyStaff extends BaseEntity<CompanyStaff> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 联系方式
	@Column(name = "phone")
	private String phone;

	// 备注
	@Column(name = "note")
	private String note;

	// 店面
	@Column(name = "store")
	private String store;

	// 真实姓名
	@Column(name = "realname")
	private String realname;

	// 身份证号
	@Column(name = "idcard")
	private String idcard;

	// 公司ID
	@Column(name = "company_id")
	private Long companyId;

	// 工作店面
	@Column(name = "workstore")
	private String workstore;

	// 打卡规则ID
	@Column(name = "attendance_id")
	private String attendanceId;

	// 工作的组ID
	@Column(name = "grouping")
	private String grouping;

	// 可看组id
	@Column(name = "store_grouping")
	private String storegrouping;

	// 城市ID
	@Column(name = "city_id")
	private String cityId;

	// 可看房源的城市
	@Column(name = "city_list")
	private String cityList;

	// 用户ID
	@Column(name = "user_id")
	private Long userId;

}
