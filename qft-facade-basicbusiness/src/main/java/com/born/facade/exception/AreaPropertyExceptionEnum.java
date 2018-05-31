package com.born.facade.exception;

import com.born.util.result.RespCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AreaPropertyExceptionEnum implements RespCode {

	ADD_AREA_PROPERTY("20110","添加楼盘地址异常"),
	UPDATE_AREA_PROPERTY("20111","修改楼盘地址异常"),
	DELETE_AREA_PROPERTY("20112","删除楼盘地址异常");

	private String code;
	private String msg;
}
