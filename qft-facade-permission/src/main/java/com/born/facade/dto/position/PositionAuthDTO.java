package com.born.facade.dto.position;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class PositionAuthDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	/**
	 * 职位ID
	 */
	private String positionId;

	/**
	 * 权限ID
	 */
	private String authorityId;

	/**
	 * 创建人
	 */
	private Date createTime;

	/**
	 * 创建人ID
	 */
	private String createrId;

}
