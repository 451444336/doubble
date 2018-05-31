package com.born.facade.exception;

import com.born.util.result.RespCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * 
* @ClassName: DicExcepionEnum 
* @Description: 字典异常枚举 
* @author lijie 
* @date 2018年5月31日 下午4:11:28 
*
 */
@Getter
@AllArgsConstructor
public enum DicExcepionEnum implements RespCode {
	
	ADD_DIC_ITEM_ERROR("30011","添加字典数据异常"),
	
	DEL_DIC_ITEM_ERROR("30012","删除字典数据异常"),
	
	UNABLE_DEL("30013","当前字典不能删除");
	
	private String code;
    private String msg;
}
