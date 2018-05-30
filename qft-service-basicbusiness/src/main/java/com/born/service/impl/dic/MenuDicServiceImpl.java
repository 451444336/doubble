package com.born.service.impl.dic;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.core.base.BaseModel;
import com.born.core.base.BaseService;
import com.born.core.base.DataBaseParameters;
import com.born.entity.dic.MenuDic;
import com.born.facade.service.dic.IMenuDicService;
import com.born.mapper.MenuDicMapper;
/**
 * 
* @ClassName: MenuDicServiceImpl 
* @Description: 菜单字典服务 
* @author lijie 
* @date 2018年5月30日 下午6:32:18 
*
 */
@Service(version = "1.0.0")
public class MenuDicServiceImpl extends BaseService<BaseModel, MenuDic> implements IMenuDicService {

	@Autowired
	private MenuDicMapper menuDicMapper;

	@Override
	protected DataBaseParameters<MenuDic> getDataBaseParameters() {
		DataBaseParameters<MenuDic> result = new DataBaseParameters<>();
		result.setEntityClass(MenuDic.class);
		result.setMapper(menuDicMapper);
		return result;
	}

}
