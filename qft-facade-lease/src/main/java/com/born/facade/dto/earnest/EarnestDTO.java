package com.born.facade.dto.earnest;

import java.math.BigDecimal;
import java.util.Date;

import com.born.core.base.BaseModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName: EarnestDTO
 * @Description: 应收房租请求参数
 * @author 明成
 * @date 2018年5月30日 上午11:12:45
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "应收房租请求参数")
public class EarnestDTO extends BaseModel {
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
	 * 合同开始时间
	 */
	private Date starTime;

	/**
	 * 合同结束时间
	 */
	private Date endTime;

	/**
	 * 租赁期限年
	 */
	private Integer limitYear;

	/**
	 * 租赁期限月
	 */
	private Integer limitMonth;

	/**
	 * 租赁期限天
	 */
	private Integer limitDay;

	/**
	 * 定金金额
	 */
	private BigDecimal earnestMoney;

	/**
	 * 定金有效期
	 */
	private String validTime;

	/**
	 * 交定时间
	 */
	private Date earnestTime;

	/**
	 * 押金
	 */
	private BigDecimal depositMoney;

	/**
	 * 约定房租
	 */
	private BigDecimal rentMoney;

	/**
	 * 缴费方式
	 */
	private String pay_method;

	/**
	 * 定金状态 0 未签约 1 已签约 2手动取消 3 自动取消
	 */
	private Byte earnestStatus;
	/**
	 * 租客ID
	 */
	private Long tenantId;
	/**
	 * 业务员ID
	 */
	private Long salesmanId;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 身份证
	 */
	private String idCard;
	/**
	 * 联系电话
	 */
	private String phone;

	/**
	 * 房间ID
	 */
	private Long roomId;
}
