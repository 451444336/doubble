package com.born.facade.service.configset;

import com.born.core.entity.UserData;
import com.born.facade.dto.configset.DefaultSetDTO;
import com.born.facade.dto.configset.RentFreePeriodDTO;
import com.born.util.result.Result;

/**
 * 
 * @ClassName: IConfigSetService
 * @Description: 通用设置服务
 * @author 张永胜
 * @date 2018年5月29日 下午3:12:52
 * @version 1.0
 */
public interface IConfigSetService {

	/**
	 * 通用保存默认设置
	 * 
	 * @Title: saveDefaultSet
	 * @Description: 保存默认设置
	 * @param type标识集中、整租、合租
	 * @param companyId公司ID
	 * @param defaultSet页面设置的值
	 * @return
	 * @author 张永胜
	 * @return Result
	 * @throws Exception
	 * @date 2018年5月29日 下午5:55:02
	 */
	Result saveDefaultSet(UserData data, DefaultSetDTO defaultSet) ;

	/**
	 * 
	 * @Title: saveRentFreePeriod
	 * @Description: 免租期模式
	 * @param type标识集中、整租、合租
	 * @param companyId公司ID
	 * @param rentFreeDTO页面设置的值
	 * @return
	 * @author 张永胜
	 * @return Result
	 * @throws Exception 
	 * @date 2018年5月30日 下午2:49:22
	 */
	Result saveRentFreePeriod(UserData data, RentFreePeriodDTO rentFreeDTO) ;
}
