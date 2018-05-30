package com.born.facade.dto.income;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

import com.born.core.base.BaseModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
* @ClassName: IncomeDTO 
* @Description: 应收房租请求参数
* @author 明成 
* @date 2018年5月29日 下午2:31:33 
* @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(value="应收房租请求参数")
public class IncomeDTO extends BaseModel {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 创建人ID
	 */
	private Long createrId;
	/**
	 * 修改时间
	 */
	private Date updateTime;
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
	 * 房间号
	 */
	private Long roomId;
	
	/**
	 * 租客编号
	 */
	private Long tenantsId;
	
	/**
	 * 缴纳次数
	 */
	private Long payCount;
	
	/**
	 * 收款时间
	 */
	private Date collectionDate;
	
	/**
	 * 开始有效期
	 */
	private Date startValidDate;
	
	/**
	 * 结束有效期
	 */
	private Date endValidDate;
	
	/**
	 * 缴费金额
	 */
	private BigDecimal payMoney;
	
	/**
	 * 物管费
	 */
	private BigDecimal propertyCost;
	
	/**
	 * 预存费
	 */
	private BigDecimal storageCost;
	
	/**
	 * 服务费
	 */
	private BigDecimal serviceCost;
	
	/**
	 * 其他费
	 */
	private BigDecimal otherCost;
	
	/**
	 * 其他费2
	 */
	private BigDecimal otherCostSecond;
	
	/**
	 * 欠款金额
	 */
	private BigDecimal debtMoney;
	
	/**
	 * 还款时间
	 */
	private Date repayDate;
	
	/**
	 * 收付状态 1未收 2收部分 3 已收完
	 */
	private Byte payStatus;
	
	
	/**
	 * 实收款
	 */
	private BigDecimal realMoney;
	
	
	/**
	 * 备注金额
	 */
	private String remark;
	
	/**
	 * 是否发送过催租短信  0未发  1已发 
	 */
	private Byte smsType;
	
	/**
	 * 滞纳金
	 */
	private BigDecimal lateFee;
}
