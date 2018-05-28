package com.born.facade.service.code;

import com.born.core.base.BaseModel;
import com.born.core.base.IBaseService;
import com.born.facade.dto.code.CodeDTO;
import com.born.util.result.Result;

/**
 * 
* @ClassName: ICodeService 
* @Description: 编号设置服务
* @author 明成 
* @date 2018年5月28日 上午11:12:11 
* @version 1.0
 */
public interface ICodeService extends IBaseService<BaseModel>{

	/**
	 * 
	* @Title: getCodeList 
	* @Description: 根据公司获取店面编号设置 
	* @param @param companyId
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月28日 下午2:49:10 
	* @throws
	 */
	Result getCodeList(CodeDTO dto);
	
	/**
	 * 
	* @Title: updateCode 
	* @Description: 修改编号设置
	* @param @param dto
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月28日 下午3:28:39 
	* @throws
	 */
	Result updateCode(CodeDTO dto);
	
	/**
	 * 
	* @Title: addCode 
	* @Description: 添加编号设置 
	* @param @param dto
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月28日 下午3:29:32 
	* @throws
	 */
	Result addCode(CodeDTO dto);
}
