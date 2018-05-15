package com.born.facade.exception;

import com.born.util.result.RespCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * 
* @ClassName: OrderExceptionEnum 
* @Description: 订单相关异常枚举 
* @author lijie 
* @date 2018年5月15日 下午12:56:53 
*
 */
@Getter
@AllArgsConstructor
public enum OrderExceptionEnum implements RespCode {
	
	ADD_ORDER_ERROR("20001", "添加订单异常")
    ;
	private String code;
    private String msg;

}
