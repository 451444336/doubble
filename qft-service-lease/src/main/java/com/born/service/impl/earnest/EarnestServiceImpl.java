package com.born.service.impl.earnest;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.core.base.BaseModel;
import com.born.core.base.BaseService;
import com.born.core.base.DataBaseParameters;
import com.born.entity.earnest.Earnest;
import com.born.facade.dto.income.EarnestDTO;
import com.born.facade.service.earnest.IEarnestService;
import com.born.mapper.EarnestMapper;
import com.born.util.result.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * 
* @ClassName: EarnestServiceImpl 
* @Description: 编号设置实现
* @author 明成 
* @date 2018年5月28日 上午11:27:28 
* @version 1.0
 */
@Slf4j
@Service(version = "1.0.0")
public class EarnestServiceImpl extends BaseService<BaseModel, Earnest> implements IEarnestService {

	@Autowired
	private EarnestMapper earnestMapper;

	@Override
	protected DataBaseParameters<Earnest> getDataBaseParameters() {
		DataBaseParameters<Earnest> dataBase = new DataBaseParameters<>();
		dataBase.setEntityClass(Earnest.class);
		dataBase.setMapper(earnestMapper);
		return dataBase;
	}

	@Override
	public Result getPageList(EarnestDTO dto) {
		return null;
	}

	@Override
	public Result updateEarnest(EarnestDTO dto) {
		return null;
	}

	@Override
	public Result addEarnest(EarnestDTO dto) {
		return null;
	}


}
