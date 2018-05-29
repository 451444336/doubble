package com.born.facade.service;

import com.born.core.base.BaseModel;
import com.born.core.base.IBaseService;
import com.born.facade.dto.focus.tenants.FocusRegDTO;
import com.born.util.result.Result;

/**
 * 
 * @ClassName: IFocusService
 * @Description: 集中租赁服务
 * @author 张永胜
 * @date 2018年5月28日 下午2:28:25
 * @version 1.0
 */
public interface IFocusService extends IBaseService<BaseModel> {

	/**
	 * 
	 * @Title: insertTenants
	 * @Description: 集中租客登记
	 * @param focusReg
	 * @return
	 * @author 张永胜
	 * @return Result
	 * @throws Exception 
	 * @date 2018年5月28日 下午3:02:54
	 */
	Result insertTenants(FocusRegDTO focusReg) throws Exception;
}
