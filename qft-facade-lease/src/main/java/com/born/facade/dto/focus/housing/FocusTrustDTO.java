package com.born.facade.dto.focus.housing;

import java.math.BigDecimal;
import java.util.Date;

import com.born.core.page.PageBean;

import lombok.Data;

/**
 * 
 * @description 集中整租房源托管信息实体dto
 * @author 黄伟
 * @date 2018年5月28日 上午10:32:00
 *
 */
@Data
public class FocusTrustDTO extends PageBean{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4962738393203190388L;
	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 创建人ID
	 */
	private Long createrId;
	/**
     * 创建时间
     */
    private String createTime;
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
	 * 房源ID
	 */
	private Long housingId;
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
	 * 递增约定
	 */
	private byte addAppoint;
	/**
	 * 递增方式
	 */
	private byte addType;
	/**
	 * 递增金额
	 */
	private double addMoney;
	/**
	 * 不规则递增ID
	 */
	private Long addIrregularId;
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
	 * 提前几天
	 */
	private double beforeTime;
	/**
	 * 延后几天
	 */
	private double postponeTime;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
