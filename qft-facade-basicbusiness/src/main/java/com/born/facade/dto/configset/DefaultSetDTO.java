package com.born.facade.dto.configset;

import java.io.Serializable;

import lombok.Data;

/**
 * 默认设置
 * 
 * @ClassName: DefaultSetDTO
 * @Description: 通用设中默认设置
 * @author 张永胜
 * @date 2018年5月29日 下午3:39:40
 * @version 1.0
 */
@Data
public class DefaultSetDTO implements Serializable {

	/**
	 * 这里是默认设置中一一对应的设置项(有些字段名简化了的)
	 * 更新添加都是这个实体
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 标识处理那块的业务 如：1集中、2整租、3合租
	 */
	private int flag;
	
	/**
	 * 是否同步
	 */
	private Boolean isSynch = false;

	/**
	 * 合同缴房租提前天数设置
	 */
	private Long landlordAdvanceId;//房东缴费提前时间设置
	private Integer landlordAdvanceDay;//单位: 天
	private Long isFixedSetId;//固定缴费时间设置
	private Boolean isFixedSetValue;// true 或 false
	private Long fixedDayDataId;//固定缴费时间设置
	private String fixedDayDataValue; //的数据 例 1:15:10;16:31:25
	private Long tenantsAdvanceId;//租客缴费提前时间
	private Integer tenantsAdvanceDay;//单位: 天
	
	/**
	 * 合同下载类型设置
	 */
	private Long downLoadTypeId;
	private String downLoadTypeValue;//例 word、pdf 等
	
	/**
	 * 房东租客录入保存后弹出设置
	 */
	private Long tenantsEjectSetId;//租客
	private Boolean tenantsEjectSetValue;
	private Long landlordEjectSetId;//房东
	private Boolean landlordEjectSetValue;
	
	/**
	 * 默认值设置
	 */
	private Long depositSetId;//押金默认值
	private Boolean depositSetValue;//例 true 或 false
	private Long psFeeSetId;//物业、预存费默认
	private Boolean psFeeSetValue;//例 true 或 false
	private Long pmFeeId;//物管费
	private Double pmFeeValue;//例 40 元 单位: 元/月
	private Long pmFeeModeId;//物管费缴费方式
	private String pmFeeModeValue;//例如：随房租支付、不付费
	private Long preDepositFeeId;//预存费
	private Double preDepositFeeValue;//例 200.5 元 单位: 元/月
	private Long preDepositFeeModeId;//预存费缴费方式
	private String preDepositFeeModeValue;//例如：随房租支付、不付费值
	
	/**
	 * 服务费默认值设置
	 */
	private Long serviceTypeSetId;//按金额/按百分比 [单选]
	private Double serviceTypeSetValue;//例 true 或 false
	private Long serviceChargeId;//金额或百分比
	private Double serviceChargeValue;//例 50 元 或 35%
	private Long serviceModeId;//服务费缴费方式
	private String serviceModeValue;//例如：随房租支付、不付费值
	
}
