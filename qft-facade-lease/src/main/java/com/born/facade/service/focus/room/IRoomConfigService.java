package com.born.facade.service.focus.room;

import com.born.facade.dto.focus.room.RoomConfigDTO;
import com.born.util.result.Result;

/**
 * 
 * @description 房间配置接口
 * @author 黄伟
 * @date 2018年5月29日 下午3:11:30
 */
public interface IRoomConfigService{

	/**
	 * 
	 * @Title
	 * @param
	 * @Description 添加或修改房间配置
	 * @author 黄伟
	 * @return
	 * @date 2018年5月29日 下午3:28:59
	 */
	Result addOrUpdate(RoomConfigDTO dto);
	
	/**
	 * 
	 * @Title
	 * @param
	 * @Description 根据房间配置ID删除房间配置
	 * @author 黄伟
	 * @return
	 * @date 2018年5月29日 下午3:29:09
	 */
	Result deleteById(Long id);
	
	/**
	 * 
	 * @Title
	 * @param 父级ID
	 * @Description 根据房间配置父ID查询房间配置信息
	 * @author 黄伟
	 * @return
	 * @date 2018年5月29日 下午3:29:47
	 */
	Result getConfigByParentId(Long parentId);
	
	/**
	 * 
	 * @Title
	 * @param
	 * @Description 查询房间配置信息
	 * @author 黄伟
	 * @return
	 * @date 2018年5月29日 下午3:30:04
	 */
	Result getConfigAll(RoomConfigDTO dto); 
}
