package com.born.facade.dto.expend;

import java.math.BigDecimal;
import java.util.Date;

import com.born.core.base.BaseModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName: ExpendDTO
 * @Description: 应支房租请求参数
 * @author 明成
 * @date 2018年5月30日 上午11:11:50
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "应支房租请求参数")
public class ExpendDTO extends BaseModel {
	private static final long serialVersionUID = 1L;
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
