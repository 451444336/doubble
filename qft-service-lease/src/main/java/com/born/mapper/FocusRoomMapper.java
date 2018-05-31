package com.born.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.born.core.base.BaseMapper;
import com.born.entity.focus.room.FocusRoom;
import com.born.facade.dto.focus.room.FocusRoomDTO;
import com.born.facade.vo.focus.room.FocusRoomVO;

/**
 * 
 * @description 集中整租房间信息Mapper
 * @author 黄伟
 * @date 2018年5月29日 下午5:30:59
 */
@Repository
public interface FocusRoomMapper extends BaseMapper<FocusRoom> {

	/**
	 * 
	 * @Title
	 * @param
	 * @Description 添加或修改房间信息
	 * @author 黄伟
	 * @return
	 * @date 2018年5月29日 下午6:33:40
	 */
	int insertOrUpdate(FocusRoomDTO dto);

	/**
	 * 
	 * @Title
	 * @param
	 * @Description 删除房间
	 * @author 黄伟
	 * @return
	 * @date 2018年5月29日 下午6:34:34
	 */
	int deleteById(Long id);

	/**
	 * 获取集中整租房间列表
	 * 
	 * @param dto
	 * @return
	 */
	List<FocusRoomVO> selectRoomList(FocusRoomDTO dto);

	/**
	 * 
	 * @Title: getRoomCreateTimeById
	 * @Description: 获取房间创建时间
	 * @param roomId
	 *            房间ID
	 * @param companyId
	 *            公司ID
	 * @return
	 * @author 张永胜
	 * @return Date
	 * @date 2018年5月29日 上午11:05:32
	 */
	Date getRoomCreateTimeById(@Param(value = "roomId") long roomId, @Param(value = "companyId") long companyId);

	/**
	 * 
	 * 
	 * @Title: updateRoomStatusById
	 * @Description: 更新房间状态、定金状态
	 * @param roomId
	 *            房间ID
	 * @param companyId
	 *            公司ID
	 * @return
	 * @author 张永胜
	 * @return int
	 * @date 2018年5月29日 上午11:05:08
	 */
	int updateRoomStatusById(@Param(value = "roomId") long roomId, @Param(value = "companyId") long companyId);

	/**
	 * 
	* @Title: updateRoomLastTime 
	* @Description: 更新房间的最后一次租出时间为租客上次租出时间
	* @param roomId
	* @param companyId
	* @param lastTime
	* @return 
	* @author 张永胜
	* @return int
	* @date 2018年5月31日 上午11:43:34
	 */
	int updateRoomLastTime(@Param(value = "roomId") long roomId, @Param(value = "companyId") long companyId,
			@Param(value = "lastTime") Date lastTime);
}
