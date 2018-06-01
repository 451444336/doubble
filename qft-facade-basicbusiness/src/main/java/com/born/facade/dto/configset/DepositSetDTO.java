package com.born.facade.dto.configset;

import java.io.Serializable;

import lombok.Data;

/***
 * 
 * @ClassName: DepositSetDTO
 * @Description: 定金设置
 * @author 张永胜
 * @date 2018年6月1日 下午4:03:17
 * @version 1.0
 */
@Data
public class DepositSetDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	/**
	 * 标识处理那块的业务 如：1集中、2整租、3合租
	 */
	private int flag;

	/**
	 * 是否同步
	 */
	private Boolean isSynch = false;

	/**
	 * 超过有效期自动取消定金
	 */
	private Long tvpCancelDepositId;
	private Boolean tvpCancelDepositValue;

}
