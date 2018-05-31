package com.born.facade.dto.focus.tenants.outroom;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 
* @ClassName: TenantsOutRoomPic 
* @Description: 退房时图片
* @author 张永胜 
* @date 2018年5月31日 上午10:49:41 
* @version 1.0
 */
@Data
public class TenantsOutRoomPic implements Serializable {
	
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	/**
	 * 水电气底数图片集合
	 */
	private List<String> wgePics;
	
	/**
	 * 退房状态图片集合
	 */
	private List<String> outRoomStatePics;
}
