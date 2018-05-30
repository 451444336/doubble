package com.born.service.impl.expend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.core.base.BaseModel;
import com.born.core.base.BaseService;
import com.born.core.base.DataBaseParameters;
import com.born.entity.expend.Expend;
import com.born.facade.dto.expend.ExpendDTO;
import com.born.facade.service.expend.IExpendService;
import com.born.mapper.ExpendMapper;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
* @ClassName: ExpendServiceImpl 
* @Description: 应支房租实现
* @author 明成 
* @date 2018年5月28日 上午11:27:28 
* @version 1.0
 */
@Slf4j
@Service(version = "1.0.0")
public class ExpendServiceImpl extends BaseService<BaseModel, Expend> implements IExpendService {

	@Autowired
	private ExpendMapper earnestMapper;

	@Override
	protected DataBaseParameters<Expend> getDataBaseParameters() {
		DataBaseParameters<Expend> dataBase = new DataBaseParameters<>();
		dataBase.setEntityClass(Expend.class);
		dataBase.setMapper(earnestMapper);
		return dataBase;
	}

	@Override
	@Transactional
	public Result batchAddExpend(List<ExpendDTO> list, String companyId,Long createrId) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		if(list==null) {
			result.setMessage("应支房租数据不能为空");
			return result;
		}
		try {
			List<Expend> inserts = new ArrayList<>(list.size());
			Expend insert = new Expend();
			for (ExpendDTO record : list) {
				BeanUtils.copyProperties(record, insert);
				insert.setCompanyId(companyId);
				insert.setCreaterId(createrId);
				insert.setCreateTime(new Date());
				inserts.add(insert);
			}
			earnestMapper.insertList(inserts);
			ResultUtil.setResult(result, RespCode.Code.SUCCESS);
		} catch (Exception e) {
			log.error("应支房租添加失败（ExpendServiceImpl.batchAddExpend）.......................", e);
		}
		return result;
	}

	@Override
	@Transactional
	public Result batchUpdateExpend(List<ExpendDTO> list, String companyId,Long createrId) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		if(list==null) {
			result.setMessage("应支房租数据不能为空");
			return result;
		}
		try {
			Expend insert = new Expend();
			for (ExpendDTO record : list) {
				BeanUtils.copyProperties(record, insert);
				if(insert.getId()==null) {
					result.setMessage("应支房租数据主键不能为空");
					return result;
				}
				insert.setCompanyId(companyId);
				insert.setUpdaterId(createrId);
				insert.setUpdateTime(new Date());
				earnestMapper.updateByPrimaryKeySelective(insert);
			}
			ResultUtil.setResult(result, RespCode.Code.SUCCESS);
		} catch (Exception e) {
			log.error("应支房租添加失败（ExpendServiceImpl.batchUpdateExpend）.......................", e);
		}
		return result;
	}

}
