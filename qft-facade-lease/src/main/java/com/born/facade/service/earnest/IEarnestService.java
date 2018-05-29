package com.born.facade.service.earnest;

import com.born.core.base.BaseModel;
import com.born.core.base.IBaseService;
import com.born.facade.dto.earnest.EarnestDTO;
import com.born.util.result.Result;

/**
 * 
* @ClassName: IEarnestService 
* @Description: 定金设置服务
* @author 明成 
* @date 2018年5月28日 上午11:12:11 
* @version 1.0
 */
public interface IEarnestService extends IBaseService<BaseModel>{
	
	

	/**
	 * 
	* @Title: getPageList 
	* @Description: 分页列表查询
	* @param @param dto
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月28日 下午4:34:34 
	* @throws
	 */
	Result getPageList(EarnestDTO dto);
	
	/**
	 * 
	* @Title: updateEarnest 
	* @Description: 修改定金设置
	* @param @param dto
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月28日 下午3:28:39 
	* @throws
	 */
	Result updateEarnest(EarnestDTO dto);
	
	/**
	 * 
	* @Title: addEarnest 
	* @Description: 添加定金设置 
	* @param @param dto
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月28日 下午3:29:32 
	* @throws
	 */
	Result addEarnest(EarnestDTO dto);
}
