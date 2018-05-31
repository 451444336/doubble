package com.born.facade.dto;

import java.io.Serializable;

import lombok.Data;
/**
 * 
* @ClassName: ValidateDTO 
* @Description: 用户操作传输实体 
* @author lijie 
* @date 2018年5月7日 下午2:26:14 
*
 */
@Data
public class ValidateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户ID
	 */
	private String fieldId;
	/**
	 * 登录IP
	 */
	private String fieldValue;
	
}
