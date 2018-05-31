package com.born.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.born.core.base.BaseMapper;
import com.born.entity.focus.tenants.TenantsInfo;

/**
 * 
 * @ClassName: FocusTenantsMapper
 * @Description: 集中租客服务类
 * @author 张永胜
 * @date 2018年5月28日 下午3:37:55
 * @version 1.0
 */
@Repository
public interface FocusTenantsInfoMapper extends BaseMapper<TenantsInfo> {

	/**
	 * 
	 * @Title: getTenantsLastTimeById
	 * @Description: 获取租客上次的租房时间
	 * @param roomId 房间ID
	 * @param companyId 公司ID
	 * @return
	 * @author 张永胜
	 * @return Date
	 * @date 2018年5月31日 上午11:42:32
	 */
	Date getTenantsLastTimeById(@Param(value = "roomId") long roomId, @Param(value = "companyId") long companyId);
}
