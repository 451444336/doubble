package com.born.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.born.core.base.BaseMapper;
import com.born.entity.income.Income;
import com.born.facade.dto.income.IncomeDTO;
import com.born.facade.vo.Income.IncomeVO;

/**
 * 
* @ClassName: IncomeMapper 
* @Description: 应收房租
* @author 明成 
* @date 2018年5月28日 上午11:53:59 
* @version 1.0
 */
@Repository
public interface IncomeMapper extends BaseMapper<Income> {


	/**
	 * 
	* @Title: selectCodeSet 
	* @Description: 查询应收房租列表
	* @param @param dto
	* @param @return
	* @author 明成
	* @return List<IncomeVO>
	* @date 2018年5月28日 下午2:31:56 
	* @throws
	 */
	List<IncomeVO> selectIncomeList(IncomeDTO dto);
}
