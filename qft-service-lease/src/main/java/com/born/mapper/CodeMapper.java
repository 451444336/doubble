package com.born.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.born.core.base.BaseMapper;
import com.born.entity.code.Code;

/**
 * 
* @ClassName: CodeMapper 
* @Description: 编号设置
* @author 明成 
* @date 2018年5月28日 上午11:53:59 
* @version 1.0
 */
@Repository
public interface CodeMapper extends BaseMapper<Code> {


	/**
	 * 
	* @Title: selectCodeSet 
	* @Description: 查询店面编号设置
	* @param @param companyId
	* @param @return
	* @author 明成
	* @return List<Code>
	* @date 2018年5月28日 下午2:31:56 
	* @throws
	 */
	List<Code> selectCodeSet(String companyId);
}