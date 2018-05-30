package com.born.facade.service.income;

import com.born.core.base.BaseModel;
import com.born.core.base.IBaseService;
import com.born.util.result.Result;

/**
 * 
* @ClassName: IIcomeService 
* @Description: 应收房租服务
* @author 明成 
* @date 2018年5月28日 上午11:12:11 
* @version 1.0
 */
public interface IIncomeService extends IBaseService<BaseModel>{
	/**
	 * 
	* @Title: mergeIncome 
	* @Description: 合并应收列表 
	* @param @param id
	* @param @param oneId
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月29日 下午4:59:02 
	* @throws
	 */
	Result mergeIncome(Long id, Long oneId);

}
