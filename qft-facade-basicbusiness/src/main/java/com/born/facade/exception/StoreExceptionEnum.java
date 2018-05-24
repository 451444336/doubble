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
public enum StoreExceptionEnum implements RespCode {
	
	ADD_STORE_ERROR("20011", "添加店面异常"),
	UPDATE_STORE_ERROR("20012", "修改店面异常"),
	DELETE_STORE_ERROR("20013", "删除店面异常"),
	ADD_GROUP_ERROR("20014", "批量添加店面分组异常"),
	UPDATE_GROUP_ERROR("20015", "修改店面分组异常"),
	DELETE_GROUP_ERROR("20016", "删除店面分组异常");
	private String code;
    private String msg;

}
