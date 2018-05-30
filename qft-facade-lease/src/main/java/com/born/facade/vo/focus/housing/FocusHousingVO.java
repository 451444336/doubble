package com.born.facade.vo.focus.housing;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * @Description: 集中整租房源vo
 * @author 黄伟
 * @date 2018年5月25日 下午6:15:21
 */
@Data
public class FocusHousingVO{

	/**
	 * 主键ID
	 */
	private Long id;
	/**
	 * 创建人/录入人ID
	 */
	private Long createrId;
	/**
	 * 创建人/录入人名称
	 */
	private String createrName;
	/**
     * 创建/录入时间
     */
    private Date createTime;
	/**
     * 修改人ID
     */
    private Long updaterId;
    /**
	 * 修改时间
	 */
	private String updateTime;
	/**
	 * 公司ID
	 */
	private Long companyId;
	/**
	 * 物业类型 1：租赁 0：自持
	 */
	private byte focusType;
	/**
	 * 店面ID
	 */
	private Long storeId;
	/**
	 * 店面名称
	 */
	private String storeName;
	/**
	 * 分组ID
	 */
	private Long groupId;
	/**
	 * 编号
	 */
	private String houseCode;
	/**
	 * 区域
	 */
	private String houseArea;
	/**
	 * 物业地址
	 */
	private String propertyAdrr;
	/**
	 * 座栋
	 */
	private String building;
	/**
	 * 开发商
	 */
	private String developers;
	/**
	 * 开发商电话
	 */
	private String developersCall;
	/**
	 * 开发商其他电话
	 */
	private String developersOtherCall;
	/**
	 * 转款账户
	 */
	private String transferAccounts;
	/**
	 * 转款账号
	 */
	private String accountNumber;
	/**
	 * 转款银行
	 */
	private String transferBank;
	/**
	 * 楼层
	 */
	private String floor;
	/**
	 * 是否电梯房：1是， 0否
	 */
	private byte isLift;
	/**
	 * 房间数 为0表示未分配房间
	 */
	private int roomCount;
	/**
	 * 房间余量
	 */
	private int restRoomCount;
	/**
	 * 备注
	 */
	private String houseNote;
	/**
	 * 是否删除：0 正常，1删除
	 */
	private byte isDelete;
	/**
	 * 托管开始时间
	 */
	private Date hostingCreateTime;
	/**
	 * 托管到期时间
	 */
	private Date hostingEndTime;
	/**
	 * 合同期限年
	 */
	private int contractYear;
	/**
	 * 合同期限月
	 */
	private int contractMonth;
	/**
	 * 合同期限日
	 */
	private int contractDay;
	/**
	 * 递增约定：0无递增，1有递增
	 */
	private byte addAppoint;
	/**
	 * 递增方式：0 按百分比，1按金额，2不规则递增
	 */
	private byte addType;
	/**
	 * 递增金额
	 */
	private double addMoney;
	/**
	 * 托管价格
	 */
	private BigDecimal joePrice;
	/**
	 * 押金
	 */
	private BigDecimal depositMoney;
	/**
	 * 缴费方式
	 */
	private String paymentMethod;
	/**
	 * 免租期年
	 */
	private int vacancy_year;
	/**
	 * 免租期月
	 */
	private int vacancyMonth;
	/**
	 * 免租期天
	 */
	private int vacamcyDay;
	/**
	 * 业务员ID
	 */
	private Long salesmanId;
	/**
	 * 业务员名称
	 */
	private String salesmanName;
	/**
	 * 提前几天
	 */
	private double beforeTime;
	/**
	 * 延后几天
	 */
	private double postponeTime;
	
}
