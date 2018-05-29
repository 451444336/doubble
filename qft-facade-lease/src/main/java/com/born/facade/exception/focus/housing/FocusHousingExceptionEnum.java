package com.born.facade.exception.focus.housing;

import com.born.util.result.RespCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * 
* @ClassName: FocusHousingExceptionEnum 
* @Description: 集中整租相关异常枚举 
* @author 黄伟
* @date 2018年5月25日 下午6:14:53 
*
 */
@Getter
@AllArgsConstructor
public enum FocusHousingExceptionEnum implements RespCode {
	
	ADD_HOUSING_ERROR("20021", "添加房源异常"),
	UPDATE_HOUSING_ERROR("20022", "修改房源异常"),
	DELETE_HOUSING_ERROR("20023", "房东退房异常"),
	ADD_ROOM_ERROR("20024", "房间分配异常");
	private String code;
    private String msg;

}
