package com.born.facade.service.property;

import com.born.facade.dto.property.AreaPropertyDTO;
import com.born.facade.dto.property.ChangeAreaPropertyDTO;
import com.born.util.result.Result;

/**
 * 
 * @description 区域楼盘地址接口
 * @author 黄伟
 * @date 2018年5月31日 下午2:42:53
 */
public interface IAreaPropertyService {

	/**
	 * 
	 * @Title
	 * @param 楼盘地址dto
	 * @Description 添加楼盘地址
	 * @author 黄伟
	 * @return
	 * @date 2018年5月31日 下午2:43:45
	 */
	Result add(ChangeAreaPropertyDTO dto);
	
	/**
	 * 
	 * @Title
	 * @param 楼盘地址实体类
	 * @Description 修改楼盘地址
	 * @author 黄伟
	 * @return
	 * @date 2018年5月31日 下午2:44:32
	 */
	Result update(ChangeAreaPropertyDTO dto);
	
	/**
	 * 
	 * @Title
	 * @param 楼盘地址ID
	 * @Description 删除楼盘地址
	 * @author 黄伟
	 * @return
	 * @date 2018年5月31日 下午2:45:17
	 */
	Result delete(Long id);
	
	/**
	 * 
	 * @Title
	 * @param 区域名称
	 * @Description 根据区域名称查询下级区域
	 * @author 黄伟
	 * @return
	 * @date 2018年5月31日 下午2:52:21
	 */
	Result getProCityArea(String areaName);
	
	/**
	 * 
	 * @Title
	 * @param 区域名称
	 * @Description 根据区域名称获取楼盘地址
	 * @author 黄伟
	 * @return
	 * @date 2018年5月31日 下午2:53:06
	 */
	Result getAreaProperty(AreaPropertyDTO dto);
	
}
