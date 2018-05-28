package com.born.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.core.base.BaseMapper;
import com.born.core.base.BaseModel;
import com.born.core.base.BaseService;
import com.born.entity.Test;
import com.born.facade.service.ITestService;

@Service(version = "1.0.0")
public class TestServiceImpl extends BaseService<BaseModel, Test> implements ITestService {

	@Override
	protected BaseMapper<Test> getMapper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<Test> getEntityClass() {
		// TODO Auto-generated method stub
		return Test.class;
	}

}
