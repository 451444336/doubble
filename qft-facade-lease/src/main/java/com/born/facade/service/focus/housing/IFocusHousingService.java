package com.born.facade.service.focus.housing;

import com.born.facade.dto.focus.housing.FocusHousingDTO;
import com.born.util.result.Result;

/**
 * 
 * @description 集中整租接口
 * @author 黄伟
 * @date 2018年5月28日 上午10:13:09
 *
 */
public interface IFocusHousingService{

	/**
	 * 添加或修改房源
	 * @param dto
	 * @return
	 */
	Result addOrUpdate(FocusHousingDTO dto);
	
	/**
	 * 删除房源
	 * @param id
	 * @return
	 */
	Result deleteById(Long id);
	
	/**
	 * 根据房源ID查询房源信息
	 * @return
	 */
	Result getHousingById(Long id);
	/**
	 * 查询房源信息列表
	 * @param dto
	 * @return
	 */
	Result getHousingList(FocusHousingDTO dto); 
	
	/**
	 * 查询不规则递增信息
	 * @param dto
	 * @return
	 */
	Result getAddIrregularAll(Long trustId); 
}
