package com.born.entity.focus.housing;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.born.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @description 集中整租房源不规则递增实体类
 * @author 黄伟
 * @date 2018年5月28日 上午10:20:41
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "qft_add_irregular")
public class AddIrregular extends BaseEntity<AddIrregular>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -185663867061296868L;
	/**
	 * 托管信息ID
	 */
	@Column(name = "trust_id")
	private Long trustId;
	/**
	 * 递增顺序
	 */
	@Column(name = "add_number")
	private int addNumber;
	/**
	 * 递增时间
	 */
	@Column(name = "add_time")
	private Date addTime;
	/**
	 * 递增金额
	 */
	@Column(name = "add_money")
	private BigDecimal addMoney;
	/**
	 * 是否删除：0正常，1删除
	 */
	@Column(name = "is_delete")
	private byte isDelete;
	
}
