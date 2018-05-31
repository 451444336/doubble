package com.born.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.born.core.base.BaseMapper;
import com.born.entity.configset.ConfigSet;

/**
 * 通用基础设置服务
 * 
 * @ClassName: ConfigSetMapper
 * @Description: 通用基础设置服务
 * @author 张永胜
 * @date 2018年5月29日 下午3:08:41
 * @version 1.0
 */
@Repository
public interface ConfigSetMapper extends BaseMapper<ConfigSet> {

	/**
	 * 
	 * @Title: batchUpdateByIds
	 * @Description: 根据ID批量更新
	 * @param list
	 * @author 张永胜
	 * @return void
	 * @date 2018年5月30日 上午9:33:06
	 */
	void batchUpdateByIds(List<ConfigSet> list);

	/**
	 * 
	* @Title: getDefaultSetByType 
	* @Description: 获取默认设置
	* @param type 查询类型如 集中、整租
	* @param companyId 公司ID
	* @return 
	* @author 张永胜
	* @return Map<String,Object>
	* @date 2018年5月31日 下午2:57:05
	 */
	Map<String, Object> getConfigSetAllByType(@Param(value = "type") String type,
			@Param(value = "companyId") Long companyId);

}
