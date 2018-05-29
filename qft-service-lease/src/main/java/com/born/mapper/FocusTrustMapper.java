package com.born.mapper;

import com.born.core.base.BaseMapper;
import com.born.entity.focus.housing.FocusTrust;
import com.born.facade.dto.focus.housing.FocusTrustDTO;

public interface FocusTrustMapper extends BaseMapper<FocusTrust> {

	/**
	 * 添加或修改托管信息
	 * @param dto
	 * @return
	 */
	int insertOrUpdate(FocusTrustDTO dto);
	
}
