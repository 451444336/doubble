package com.born.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.born.core.base.BaseMapper;
import com.born.entity.focus.room.FocusRoom;
import com.born.facade.dto.focus.room.FocusRoomDTO;
import com.born.facade.vo.focus.housing.FocusHousingVO;

/**
 * 
 * @description 集中整租房间Mapper
 * @author 黄伟
 * @date 2018年5月28日 下午3:25:04
 */
@Repository
public interface FocusRoomMapper extends BaseMapper<FocusRoom> {

	/**
	 * 添加或修改房间
	 * 
	 * @param dto
	 * @return
	 */
	int insertOrUpdate(FocusRoomDTO dto);

	/**
	 * 根据ID删除集中在整租房间信息
	 * 
	 * @param id
	 */
	int deleteById(Long id);

	/**
	 * 获取集中整租房间列表
	 * 
	 * @param dto
	 * @return
	 */
	List<FocusHousingVO> selectHousingList(FocusRoomDTO dto);

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
}
