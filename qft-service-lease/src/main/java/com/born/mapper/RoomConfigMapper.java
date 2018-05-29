package com.born.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.born.core.base.BaseMapper;
import com.born.entity.focus.room.RoomConfig;
import com.born.facade.dto.focus.room.RoomConfigDTO;
import com.born.facade.vo.focus.room.RoomConfigVO;

/**
 * 
 * @description 房间配置Mapper
 * @author 黄伟
 * @date 2018年5月29日 下午3:19:54
 */
@Repository
public interface RoomConfigMapper extends BaseMapper<RoomConfig> {

	/**
	 * 
	 * @Title
	 * @param
	 * @Description 添加或修改房间配置
	 * @author 黄伟
	 * @return
	 * @date 2018年5月29日 下午3:28:34
	 */
	int insertOrUpdate(RoomConfigDTO dto);

	/**
	 * 
	 * @Title
	 * @param 房间配置ID
	 * @Description 根据房间配置ID删除房间配置
	 * @author 黄伟
	 * @return
	 * @date 2018年5月29日 下午3:28:14
	 */
	int deleteById(Long id);

	/**
	 * 
	 * @Title
	 * @param 父级ID
	 * @Description 根据房间配置父ID查询房间配置信息
	 * @author 黄伟
	 * @return
	 * @date 2018年5月29日 下午3:27:39
	 */
	List<RoomConfigVO> selectConfigByParentId(Long parentId);
	/**
	 * 
	 * @Title
	 * @param
	 * @Description 查询房间配置信息
	 * @author 黄伟
	 * @return
	 * @date 2018年5月29日 下午3:27:08
	 */
	List<RoomConfigVO> selectConfigAll(RoomConfigDTO dto);
}
