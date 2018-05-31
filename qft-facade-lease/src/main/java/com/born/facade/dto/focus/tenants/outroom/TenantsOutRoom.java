package com.born.facade.dto.focus.tenants.outroom;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * 
 * @ClassName: TenantsCheckOutDTO
 * @Description: 租客退房
 * @author 张永胜
 * @date 2018年5月31日 上午10:23:53
 * @version 1.0
 */
@Data
public class TenantsOutRoom implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	/** 房源ID */
	private Long housingId;

	/** 房间ID */
	private Long roomId;

	/** 租客信息ID */
	private Long tenantsId;

	/** 退房时间 */
	private Date checkOutTime;

	/** 退房性质 */
	private String checkOutType;

	/** 水止数 */
	private BigDecimal water;

	/** 电止数 */
	private BigDecimal electricity;

	/** 气止数 */
	private BigDecimal gas;

	/** 物管费缴到什么时间 */
	private Date contentFeeTime;

	/** 退还押金 (默认值为: 0.00) */
	private BigDecimal backDepositMoney;

	/** 退还租金 (默认值为: 0.00) */
	private BigDecimal backRentsMoney;

	/** 退还预存费 (默认值为: 0.00) */
	private BigDecimal backAdvanceMoney;

	/** 退还其他 (默认值为: 0.00) */
	private BigDecimal backOtherMoney;

	/** 扣除水费 */
	private BigDecimal waterMoney;

	/** 扣除电费 */
	private BigDecimal electricityMoney;

	/** 扣除气费 */
	private BigDecimal gasMoney;

	/** 扣除物管费 */
	private BigDecimal managementMoney;

	/** 违约金 */
	private BigDecimal liquidatedDamages;

	/** 垃圾费 */
	private BigDecimal wasteMoney;

	/** 赔偿费 */
	private BigDecimal compensation;

	/** 清洁费 */
	private BigDecimal cleanMoney;

	/** 超期天数 */
	private BigDecimal exceedDay;

	/** 超期房租费 */
	private BigDecimal exceedMoney;

	/** 应退还金额 */
	private BigDecimal backMoney;

	/** 扣除金额 */
	private BigDecimal otherMoney;

	/** 实际退还金额 */
	private BigDecimal trueBackMoney;

	/** 退房原因 */
	private String checkOutReason;

	/** 备注信息 */
	private String checkOutRemark;

}
