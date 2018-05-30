package com.born.service.impl.dic;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.core.base.BaseModel;
import com.born.core.base.BaseService;
import com.born.core.base.DataBaseParameters;
import com.born.entity.dic.DicType;
import com.born.facade.service.dic.IDicTypeService;
import com.born.mapper.DicTypeMapper;
/**
 * 
* @ClassName: DicTypeServiceImpl 
* @Description: 字典类型 
* @author lijie 
* @date 2018年5月30日 下午6:35:02 
*
 */
@Service(version = "1.0.0")
public class DicTypeServiceImpl extends BaseService<BaseModel, DicType> implements IDicTypeService {

	@Autowired
	private DicTypeMapper dicTypeMapper;

	@Override
	protected DataBaseParameters<DicType> getDataBaseParameters() {
		DataBaseParameters<DicType> result = new DataBaseParameters<>();
		result.setEntityClass(DicType.class);
		result.setMapper(dicTypeMapper);
		return result;
	}

}
