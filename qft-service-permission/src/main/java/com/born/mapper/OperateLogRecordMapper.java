package com.born.mapper;


import org.springframework.stereotype.Repository;

import com.born.core.base.BaseMapper;
import com.born.facade.dto.OperateLogRecordDTO;
import com.born.facade.entity.OperateLogRecord;

/**
 * @Description: 操作日志记录Mapper
 * @author 黄伟
 * @date 2018年5月14日 下午4:21:19
 */
@Repository
public interface OperateLogRecordMapper extends BaseMapper<OperateLogRecord> {

	/**
	 * 插入操作日志记录
	 * 
	 * @param param
	 */
	void insertOperateLogRecord(OperateLogRecordDTO dto);
}
