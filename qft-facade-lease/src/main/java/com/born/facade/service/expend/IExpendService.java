package com.born.facade.service.expend;

import java.util.List;

import com.born.core.base.BaseModel;
import com.born.core.base.IBaseService;
import com.born.facade.dto.expend.ExpendDTO;
import com.born.util.result.Result;

/**
 * 
* @ClassName: IEarnestService 
* @Description: 应支房租服务
* @author 明成 
* @date 2018年5月28日 上午11:12:11 
* @version 1.0
 */
public interface IExpendService extends IBaseService<BaseModel>{
	
	/**
	 * 
	* @Title: BatchAddExpend 
	* @Description: 批量添加应支房租
	* @param @param list
	* @param @param companyId createrId
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月30日 下午1:52:49 
	* @throws
	 */
	Result batchAddExpend(List<ExpendDTO> list ,Long companyId,Long createrId);
	
	/**
	 * 
	* @Title: BatchUpdateExpend 
	* @Description: 批量修改应支房租
	* @param @param list
	* @param @param companyId createrId
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月30日 下午1:52:49 
	* @throws
	 */
	Result batchUpdateExpend(List<ExpendDTO> list ,Long companyId,Long createrId);
	
	
}
