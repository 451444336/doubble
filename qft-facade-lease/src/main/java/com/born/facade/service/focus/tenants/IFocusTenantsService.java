package com.born.facade.service.focus.tenants;

import com.born.core.base.BaseModel;
import com.born.core.base.IBaseService;
import com.born.core.entity.UserData;
import com.born.facade.dto.focus.tenants.add.TenantsRegDTO;
import com.born.facade.dto.focus.tenants.outroom.TenantsOutRoomDTO;
import com.born.util.result.Result;

/**
 * 
 * @ClassName: IFocusService
 * @Description: 集中租赁服务
 * @author 张永胜
 * @date 2018年5月28日 下午2:28:25
 * @version 1.0
 */
public interface IFocusTenantsService extends IBaseService<BaseModel> {

	/**
	 * 
	 * @Title: insertTenants
	 * @Description: 集中租客登记
	 * @param params
	 * @return
	 * @author 张永胜
	 * @return Result
	 * @throws Exception
	 * @date 2018年5月28日 下午3:02:54
	 */
	Result saveTenants(UserData user, TenantsRegDTO params);

	/**
	 * 
	 * @Title: saveOutRoom
	 * @Description: 租客退房
	 * @param params
	 * @return
	 * @author 张永胜
	 * @return Result
	 * @date 2018年5月31日 上午10:25:28
	 */
	Result saveOutRoom(UserData user, TenantsOutRoomDTO params);

}
