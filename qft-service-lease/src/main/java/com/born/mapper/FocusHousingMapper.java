package com.born.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.born.core.base.BaseMapper;
import com.born.entity.focus.housing.FocusHousing;
import com.born.facade.dto.focus.housing.AddOrUpdHousingDTO;
import com.born.facade.dto.focus.housing.FocusHousingDTO;
import com.born.facade.dto.focus.housing.UpdRoomCountHousingDTO;
import com.born.facade.vo.focus.housing.FocusHousingVO;

/**
 * 
 * @description 集中整租房源Mapper
 * @author 黄伟
 * @date 2018年5月28日 下午3:25:04
 */
@Repository
public interface FocusHousingMapper extends BaseMapper<FocusHousing> {

	/**
	 * 添加或修改房源
	 * @param dto
	 * @return
	 */
	int insertOrUpdate(AddOrUpdHousingDTO dto);
	
	/**
	 * 
	 * @Title
	 * @param
	 * @Description 修改房间数量,默认为＋，加负号为-
	 * @author 黄伟
	 * @return
	 * @date 2018年5月30日 上午9:42:57
	 */
	int updateRoomCount(UpdRoomCountHousingDTO dto);
	
	/**
	 * 根据ID删除集中在整租房源信息
	 * @param id
	 */
	int deleteById(Long id);
	
	/**
	 * 根据房源ID查询房源信息
	 * @param id
	 * @return
	 */
	FocusHousingVO selectHousingById(Long id);
	
	/**
	 * 获取集中整租房源列表
	 * @param dto
	 * @return
	 */
	List<FocusHousingVO> selectHousingList(FocusHousingDTO dto);
}
