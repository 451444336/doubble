package com.born.facade.dto.focus.room;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

import com.born.core.page.PageBean;

import lombok.Data;

/**
 * @Description: 集中整租房源dto
 * @author 黄伟
 * @date 2018年5月25日 下午6:15:21
 */
@Data
public class FocusRoomDTO extends PageBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4715165883274928504L;
	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 创建人ID
	 */
	private Long createrId;
	/**
     * 创建时间
     */
    private Date createTime;
	/**
     * 修改人ID
     */
    private Long updaterId;
    /**
	 * 修改时间
	 */
	private Date updateTime;
    /**
	 * 公司ID
	 */
	private Long companyId;
	/**
	 * 房源ID
	 */
	private Long housingId;
	/**
	 * 序号
	 */
	private Long serialNumber;
	/**
	 * 门牌号
	 */
	private String roomNumber;
	/**
	 * 户型
	 */
	private String houseDoor;
	/**
	 * 面积
	 */
	private double insideSpace;
	/**
	 * 朝向
	 */
	private String orientation;
	/**
	 * 房间配置
	 */
	private String roomConfig;
	/**
	 * 租客ID
	 */
	private Long tenantId;
	/**
	 * 定金状态 0 未定 1 已定
	 */
	private byte eMoneyStatus;
	/**
	 * 是否已租 0未租 1已租
	 */
	private byte isLease;
	/**
	 * 定价设置人ID
	 */
	private Long priceCreaterId;
	/**
	 * 定价设置人名称
	 */
	private String priceCreaterName;
	/**
	 * 定价设置时间
	 */
	private Date priceCreateTime;
	/**
	 * 定价
	 */
	private BigDecimal pricingMoney;
	/**
	 * 押金
	 */
	private BigDecimal depositMoney;
	/**
	 * 底价设置人ID
	 */
	private Long lowpriceCreaterId;
	/**
	 * 底价设置人名称
	 */
	private String lowpriceCreaterName;
	/**
	 * 底价设置时间
	 */
	private Date lowpriceCreateTime;
	/**
	 * 底价
	 */
	private BigDecimal lowprice;
	/**
	 * 最后一次 出租时间
	 */
	private Date lastTime;
	/**
	 * 是否删除：0 正常，1删除
	 */
	private byte isDelete;
}
