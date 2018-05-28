package com.born.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.core.base.BaseModel;
import com.born.core.base.BaseService;
import com.born.core.base.DataBaseParameters;
import com.born.entity.Test;
import com.born.facade.service.ITestService;

@Service(version = "1.0.0")
public class TestServiceImpl extends BaseService<BaseModel, Test> implements ITestService {

	@Override
	protected DataBaseParameters<Test> getDataBaseParameters() {
		
		return null;
	}

}
