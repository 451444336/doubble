package com.born.entity.focus.room;

import java.math.BigDecimal;
import java.util.Date;

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
@Table(name = "qft_focus_room")
public class FocusRoom extends BaseEntity<FocusRoom>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7866833387307006884L;
	
	/**
	 * 公司ID
	 */
	@Column(name = "company_id")
	private Long companyId;
	/**
	 * 房源ID
	 */
	@Column(name = "housing_id")
	private Long housingId;
	/**
	 * 序号
	 */
	@Column(name = "serial_number")
	private Long serialNumber;
	/**
	 * 门牌号
	 */
	@Column(name = "room_number")
	private String roomNumber;
	/**
	 * 户型
	 */
	@Column(name = "house_door")
	private String houseDoor;
	/**
	 * 面积
	 */
	@Column(name = "inside_space")
	private double insideSpace;
	/**
	 * 朝向
	 */
	@Column(name = "orientation")
	private String orientation;
	/**
	 * 房间配置
	 */
	@Column(name = "room_config")
	private String roomConfig;
	/**
	 * 租客ID
	 */
	@Column(name = "tenant_id")
	private Long tenantId;
	/**
	 * 定金状态 0 未定 1 已定
	 */
	@Column(name = "e_money_status")
	private byte eMoneyStatus;
	/**
	 * 是否已租 0未租 1已租
	 */
	@Column(name = "is_lease")
	private byte isLease;
	/**
	 * 定价设置人ID
	 */
	@Column(name = "price_creater_id")
	private Long priceCreaterId;
	/**
	 * 定价设置人名称
	 */
	@Column(name = "price_creater_name")
	private String priceCreaterName;
	/**
	 * 定价设置时间
	 */
	@Column(name = "price_create_time")
	private Date priceCreateTime;
	/**
	 * 定价
	 */
	@Column(name = "pricing_money")
	private BigDecimal pricingMoney;
	/**
	 * 押金
	 */
	@Column(name = "deposit_money")
	private BigDecimal depositMoney;
	/**
	 * 底价设置人ID
	 */
	@Column(name = "lowprice_creater_id")
	private Long lowpriceCreaterId;
	/**
	 * 底价设置人名称
	 */
	@Column(name = "lowprice_creater_name")
	private String lowpriceCreaterName;
	/**
	 * 底价设置时间
	 */
	@Column(name = "lowprice_create_time")
	private Date lowpriceCreateTime;
	/**
	 * 底价
	 */
	@Column(name = "lowprice")
	private BigDecimal lowprice;
	/**
	 * 最后一次 出租时间
	 */
	@Column(name = "last_time")
	private Date lastTime;
	/**
	 * 是否删除：0 正常，1删除
	 */
	@Column(name = "is_delete")
	private byte isDelete;

}
