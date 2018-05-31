package com.born.mapper;

import java.util.List;

import com.born.core.base.BaseMapper;
import com.born.entity.property.AreaProperty;
import com.born.facade.dto.property.AreaPropertyDTO;
import com.born.facade.vo.property.AreaPropertyVO;
import com.born.facade.vo.property.ProvCityAreaVO;

public interface AreaPropertyMapper extends BaseMapper<AreaProperty> {

	 
	/**
	 * 
	 * @Title
	 * @param 楼盘ID
	 * @Description 删除楼盘地址
	 * @author 黄伟
	 * @return
	 * @date 2018年5月31日 下午3:18:05
	 */
	int deleteById(Long id);
	
	/**
	 * 
	 * @Title
	 * @param 区域名称
	 * @Description 根据区域名称查询下级区域
	 * @author 黄伟
	 * @return
	 * @date 2018年5月31日 下午3:24:09
	 */
	List<ProvCityAreaVO> seleteProCityArea(String areaName);
	
	/**
	 * 
	 * @Title
	 * @param 区域名称
	 * @Description 根据区域名称查询楼盘地址
	 * @author 黄伟
	 * @return
	 * @date 2018年5月31日 下午4:02:08
	 */
	List<AreaPropertyVO> seleteAreaProperty(AreaPropertyDTO dto);
}
