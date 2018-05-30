package com.born.entity.focus.housing;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 集中整租房间实体类
 * @author 黄伟
 * @date 2018年5月25日 下午6:15:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "qft_focus_housing")
public class FocusHousing extends BaseEntity<FocusHousing>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7866833387307006884L;
	
	/**
	 * 创建人/录入人名称
	 */
	@Column(name = "creater_name")
	private String createrName;
	/**
	 * 公司ID
	 */
	@Column(name = "company_id")
	private Long companyId;
	/**
	 * 物业类型 1：租赁 0：自持
	 */
	@Column(name = "focus_type")
	private byte focusType;
	/**
	 * 店面ID
	 */
	@Column(name = "store_id")
	private Long storeId;
	/**
	 * 店面名称
	 */
	@Column(name = "store_name")
	private String storeName;
	/**
	 * 分组ID
	 */
	@Column(name = "group_id")
	private Long groupId;
	/**
	 * 编号
	 */
	@Column(name = "house_code")
	private String houseCode;
	/**
	 * 区域
	 */
	@Column(name = "house_area")
	private String houseArea;
	/**
	 * 物业地址
	 */
	@Column(name = "property_adrr")
	private String propertyAdrr;
	/**
	 * 座栋
	 */
	@Column(name = "building")
	private String building;
	/**
	 * 开发商
	 */
	@Column(name = "developers")
	private String developers;
	/**
	 * 开发商电话
	 */
	@Column(name = "developers_call")
	private String developersCall;
	/**
	 * 开发商其他电话
	 */
	@Column(name = "developers_other_call")
	private String developersOtherCall;
	/**
	 * 转款账户
	 */
	@Column(name = "transfer_accounts")
	private String transferAccounts;
	/**
	 * 转款账号
	 */
	@Column(name = "account_number")
	private String accountNumber;
	/**
	 * 转款银行
	 */
	@Column(name = "transfer_bank")
	private String transferBank;
	/**
	 * 楼层
	 */
	@Column(name = "floor")
	private String floor;
	/**
	 * 是否电梯房：1是， 0否
	 */
	@Column(name = "is_lift")
	private byte isLift;
	/**
	 * 房间数 为0表示未分配房间
	 */
	@Column(name = "room_count")
	private int roomCount;
	/**
	 * 房间余量
	 */
	@Column(name = "rest_room_count")
	private int restRoomCount;
	/**
	 * 备注
	 */
	@Column(name = "house_note")
	private String houseNote;
	/**
	 * 是否删除：0 正常，1删除
	 */
	@Column(name = "is_delete")
	private byte isDelete;

}
