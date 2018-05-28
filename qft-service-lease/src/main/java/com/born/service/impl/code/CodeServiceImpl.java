package com.born.service.impl.code;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.core.base.BaseModel;
import com.born.core.base.BaseService;
import com.born.core.base.DataBaseParameters;
import com.born.entity.code.Code;
import com.born.facade.service.code.ICodeService;
import com.born.mapper.CodeMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 
* @ClassName: CodeServiceImpl 
* @Description: 编号设置实现
* @author 明成 
* @date 2018年5月28日 上午11:27:28 
* @version 1.0
 */
@Slf4j
@Service(version = "1.0.0")
public class CodeServiceImpl extends BaseService<BaseModel, Code> implements ICodeService {

	@Autowired
	private CodeMapper codeMapper;

	@Override
	protected DataBaseParameters<Code> getDataBaseParameters() {
		DataBaseParameters<Code> dataBase = new DataBaseParameters<>();
		dataBase.setEntityClass(Code.class);
		dataBase.setMapper(codeMapper);
		return dataBase;
	}

}
