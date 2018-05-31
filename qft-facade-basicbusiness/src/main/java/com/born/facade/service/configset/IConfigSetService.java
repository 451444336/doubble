package com.born.facade.service.configset;

import com.born.core.entity.UserData;
import com.born.facade.dto.configset.DefaultSetDTO;
import com.born.facade.dto.configset.FixPriceSetDTO;
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
	 * 默认设置
	 * 
	 * @Title: saveDefaultSet
	 * @Description: 保存默认设置
	 * @param data 用户数据
	 * @param defaultSet 提交的参数
	 * @return
	 * @author 张永胜
	 * @return Result
	 * @date 2018年5月31日 下午4:37:19
	 */
	Result saveDefaultSet(UserData data, DefaultSetDTO defaultSet);

	/***
	 * 免租期模式
	 * @Title: saveRentFreePeriod
	 * @Description: 免租期模式
	 * @param data 用户数据
	 * @param rentFreeDTO 提交的参数
	 * @return
	 * @author 张永胜
	 * @return Result
	 * @date 2018年5月31日 下午4:37:35
	 */
	Result saveRentFreePeriod(UserData data, RentFreePeriodDTO rentFreeDTO);

	/**
	 * 定价设置
	 * @Title: saveFixPriceSet
	 * @Description: 定价设置
	 * @param data 用户数据
	 * @param param 提交的参数
	 * @return
	 * @author 张永胜
	 * @return Result
	 * @date 2018年5月31日 下午4:36:23
	 */
	Result saveFixPriceSet(UserData data, FixPriceSetDTO param);
}
