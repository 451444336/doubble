package com.born.mapper;


import org.springframework.stereotype.Repository;

import com.born.core.base.BaseMapper;
import com.born.facade.dto.OperateLogAuthorityDTO;
import com.born.facade.entity.OperateLogAuthority;

/**
 * @Description: 操作日志记录Mapper
 * @author 黄伟
 * @date 2018年5月14日 下午4:21:19
 */
@Repository
public interface OperateLogAuthorityMapper extends BaseMapper<OperateLogAuthority> {

	/**
	 * 插入操作日志记录
	 * 
	 * @param param
	 */
	void insertOperateLogAuthority(OperateLogAuthorityDTO dto);
}
