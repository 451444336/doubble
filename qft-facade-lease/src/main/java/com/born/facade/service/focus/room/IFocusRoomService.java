package com.born.facade.service.focus.room;

import java.util.List;

import com.born.facade.dto.focus.room.FocusRoomDTO;
import com.born.util.result.Result;

/**
 * 
 * @description 分配房间接口
 * @author 黄伟
 * @date 2018年5月29日 下午5:24:46
 */
public interface IFocusRoomService{

	/**
	 * 
	 * @Title
	 * @param
	 * @Description 分配房间
	 * @author 黄伟
	 * @return
	 * @date 2018年5月29日 下午5:27:28
	 */
	Result batchAddOrUpdate(List<FocusRoomDTO> listDTO);
	
	/**
	 * 
	 * @Title
	 * @param
	 * @Description 删除房间
	 * @author 黄伟
	 * @return
	 * @date 2018年5月29日 下午6:38:53
	 */
	Result deleteById(Long id);
	
	/**
	 * 
	 * @Title
	 * @param
	 * @Description 查询房间信息列表
	 * @author 黄伟
	 * @return
	 * @date 2018年5月29日 下午5:27:13
	 */
	Result getRoomList(FocusRoomDTO dto); 
}
