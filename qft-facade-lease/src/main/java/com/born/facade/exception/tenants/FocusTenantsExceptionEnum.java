package com.born.facade.exception.tenants;

import com.born.util.result.RespCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * @ClassName: FocusTenantsExceptionEnum
 * @Description: 集中整租相关异常枚举
 * @author 张永胜
 * @date 2018年5月25日 下午6:14:53
 *
 */
@Getter
@AllArgsConstructor
public enum FocusTenantsExceptionEnum implements RespCode {

	SAVE_TENANTS_ERROR("20031", "保存租客信息异常"), 
	SAVE_TENANTS_OUT_ROOM_ERROR("20032", "保存租客退房异常");
	private String code;
	private String msg;

}
