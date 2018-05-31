package com.born.facade.dto.focus.tenants.outroom;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * @ClassName: TenantsOutRoomDTO
 * @Description: 退房时主要的参数body
 * @author 张永胜
 * @date 2018年5月31日 上午10:46:31
 * @version 1.0
 */
@Data
public class TenantsOutRoomDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	/**
	 * 退房信息
	 */
	private TenantsOutRoom outRoom;

	/**
	 * 退房信息图片
	 */
	private TenantsOutRoomPic outRoomPic;
}
