package com.born.mapper;

import java.util.List;

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

}
