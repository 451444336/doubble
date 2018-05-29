package com.born.facade.vo.focus.housing;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 
 * @description 集中整租房源不规则递增vo
 * @author 黄伟
 * @date 2018年5月28日 上午10:20:41
 *
 */
@Data
public class AddIrregularVO{

	
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
	 * 托管信息ID
	 */
	private Long trustId;
	/**
	 * 递增顺序
	 */
	private int addNumber;
	/**
	 * 递增时间
	 */
	private String add_time;
	/**
	 * 递增金额
	 */
	private BigDecimal addMoney;
	
}
