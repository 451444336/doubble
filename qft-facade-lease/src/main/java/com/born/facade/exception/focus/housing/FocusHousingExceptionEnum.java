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
	
	ADD_UPDATE_HOUSING_ERROR("20021", "添加或修改房源异常"),
	DELETE_HOUSING_ERROR("20022", "房东退房异常"),
	ADD_UPDATE_ROOM_ERROR("20023", "添加或修改房间异常"),
	DELETE_ROOM_ERROR("20024","删除房间异常"),
	ADD_UPDATE_CONFIG_ERROR("20025", "添加或修改房间配置异常"),
	DELETE_CONFIG_ERROR("20026", "删除房间配置异常"),
	UPDATE_ROOM_COUNT_ERROR("20027", "修改房间数量异常");
	private String code;
    private String msg;

}
