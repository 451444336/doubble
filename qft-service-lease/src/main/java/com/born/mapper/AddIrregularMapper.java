package com.born.mapper;

import java.util.List;

import com.born.core.base.BaseMapper;
import com.born.entity.focus.housing.AddIrregular;
import com.born.facade.dto.focus.housing.AddIrregularDTO;
import com.born.facade.vo.focus.housing.AddIrregularVO;

public interface AddIrregularMapper extends BaseMapper<AddIrregular> {

	/**
	 * 添加或修改托管不规则递增
	 * @param dto
	 * @return
	 */
	int insertOrUpdate(AddIrregularDTO dto);
	
	/**
	 * 根据托管信息ID查询不规则递增信息
	 * @param trustId
	 * @return
	 */
	List<AddIrregularVO> getAddIrregularAll(Long trustId);
}
