package com.born.facade.vo.expend;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * 
 * @ClassName: ExpendVO
 * @Description: 应支房租返回实体
 * @author 明成
 * @date 2018年5月30日 上午11:12:04
 * @version 1.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExpendVO {
	/**
	 * 主键id
	 */
	private Long id;

	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 创建人ID
	 */
	private Long createrId;

	/**
	 * 修改人ID
	 */
	private Long updaterId;
	/**
	 * 公司唯一ID
	 */
	private String companyId;

	/**
	 * 房源编号
	 */
	private Long housingId;

	/**
	 * 缴纳次数
	 */
	private Integer payCount;

	/**
	 * 交租时间
	 */
	private Date payDate;

	/**
	 * 有效期
	 */
	private Date validDate;

	/**
	 * 缴费金额
	 */
	private BigDecimal earnestMoney;

	/**
	 * 支付状态 1未付 2付一半 3 已付完
	 */
	private Byte payStatus;

	/**
	 * 备注
	 */
	private String remark;
}
