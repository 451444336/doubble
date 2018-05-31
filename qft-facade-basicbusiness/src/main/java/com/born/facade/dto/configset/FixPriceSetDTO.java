package com.born.facade.dto.configset;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * @ClassName: FixPriceSetDTO
 * @Description: 定价设置
 * @author 张永胜
 * @date 2018年5月31日 下午4:31:56
 * @version 1.0
 */
@Data
public class FixPriceSetDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 低于定价出租，不能录入信息
	 */
	private Long lowFixPriceNoInId;
	private Boolean lowFixPriceNoInValue;

	/**
	 * 低于规定押金出租，不能录入信息
	 */
	private Long lowDepositNoInId;
	private Boolean lowDepositNoInValue;

	/**
	 * 用退房后仍保留之前定价
	 */
	private Long outRoomIsSaveFixPriceId;
	private Boolean outRoomIsSaveFixPriceValue;

}
