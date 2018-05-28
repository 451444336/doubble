package com.born.service.impl.code;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.core.base.BaseModel;
import com.born.core.base.BaseService;
import com.born.core.base.DataBaseParameters;
import com.born.entity.code.Code;
import com.born.facade.dto.code.CodeDTO;
import com.born.facade.service.code.ICodeService;
import com.born.mapper.CodeMapper;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;

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

	@Override
	public Result getCodeList(CodeDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		String errorStr = dto.validateForm();
		if (StringUtils.isNotBlank(errorStr)) {
			result.setMessage(errorStr);
			return result;
		}
		try {
			ResultUtil.setResult(result, RespCode.Code.SUCCESS,codeMapper.selectCodeSet(dto.getCompanyId()));
		} catch (Exception e) {
			log.error("查询编号设置失败（CodeServiceImpl.getCodeList）.......................", e);
		}
		return result;
	}

	@Override
	public Result updateCode(CodeDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		String errorStr = dto.validateForm();
		if (StringUtils.isNotBlank(errorStr)) {
			result.setMessage(errorStr);
			return result;
		}
		try {
			Code code = new Code();
			BeanUtils.copyProperties(dto, code);
			ResultUtil.setResult(result, RespCode.Code.SUCCESS,codeMapper.updateByPrimaryKeySelective(code));
		} catch (Exception e) {
			log.error("修改编号设置失败（CodeServiceImpl.updateCode）.......................", e);
		}
		return result;
	}

	@Override
	public Result addCode(CodeDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		String errorStr = dto.validateForm();
		if (StringUtils.isNotBlank(errorStr)) {
			result.setMessage(errorStr);
			return result;
		}
		try {
			Code code = new Code();
			BeanUtils.copyProperties(dto, code);
			ResultUtil.setResult(result, RespCode.Code.SUCCESS,codeMapper.insertSelective(code));
		} catch (Exception e) {
			log.error("添加编号设置失败（CodeServiceImpl.addCode）.......................", e);
		}
		return result;
	}

}
