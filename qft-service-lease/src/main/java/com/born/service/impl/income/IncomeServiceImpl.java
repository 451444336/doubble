package com.born.service.impl.income;

import java.text.SimpleDateFormat;
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
import com.born.entity.income.Income;
import com.born.facade.dto.income.IncomeDTO;
import com.born.facade.service.income.IIncomeService;
import com.born.mapper.IncomeMapper;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @ClassName: IncomeServiceImpl
 * @Description: 应收房租实现
 * @author 明成
 * @date 2018年5月28日 上午11:27:28
 * @version 1.0
 */
@Slf4j
@Service(version = "1.0.0")
public class IncomeServiceImpl extends BaseService<BaseModel, Income> implements IIncomeService {

	@Autowired
	private IncomeMapper incomeMapper;

	@Override
	protected DataBaseParameters<Income> getDataBaseParameters() {
		DataBaseParameters<Income> dataBase = new DataBaseParameters<>();
		dataBase.setEntityClass(Income.class);
		dataBase.setMapper(incomeMapper);
		return dataBase;
	}

	@Override
	@Transactional
	public Result mergeIncome(Long id, Long oneId) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		if (id==null||oneId==null) {
			result.setMessage("应收ID不能为空");
			return result;
		}
		try {
			//获取最后一条应收房租数据
			Income income = incomeMapper.selectByPrimaryKey(id);
			//获取倒数第二条应收房租数据
			Income incomeOne = incomeMapper.selectByPrimaryKey(oneId);
			if(income==null||incomeOne==null) {
				result.setMessage("无应收房租数据");
				return result;
			}
			//如果有一条为押金数据则不允许
			if(income.getPayCount()==0||incomeOne.getPayCount()==0) {
				result.setMessage("不能合并押金数据");
				return result;
			}
			//合并结束时间
			incomeOne.setEndValidDate(income.getEndValidDate());
			//合并收款金额
			incomeOne.setPayMoney(income.getPayMoney().add(incomeOne.getPayMoney()));
			//合并物管费
			incomeOne.setPropertyCost(income.getPropertyCost().add(incomeOne.getPropertyCost()));
			//合并服务费
			incomeOne.setServiceCost(income.getServiceCost().add(incomeOne.getServiceCost()));
			//合并预存费
			incomeOne.setOtherCost(income.getOtherCost().add(incomeOne.getOtherCost()));
			//合并其它费 ---------------------------------------------------------------------先不做
			//incomeOne.setServiceCost(income.getServiceCost().add(incomeOne.getServiceCost()));
			//添加备注
			SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
			SimpleDateFormat formatValid = new SimpleDateFormat("yyyy-MM-dd");
			incomeOne.setRemark("管理员于"+format.format(new Date())+"操作了应收房租数据合并（缴纳次数：第"+income.getPayCount()+"次缴纳，"
					+ "收款时间："+income.getCollectionDate()+",有效期："+formatValid.format(income.getStartValidDate())+"至"
					+formatValid.format(income.getEndValidDate())+",收款金额："+income.getPayMoney()+",物管费/其它费："
							+income.getPropertyCost()+",服务费："+income.getServiceCost()+",预存费："+income.getOtherCost()+"）");
			//先修改倒数第二条数据
			incomeMapper.updateByPrimaryKeySelective(incomeOne);
			//删除最后一条
			incomeMapper.deleteByPrimaryKey(income);
			ResultUtil.setResult(result, RespCode.Code.SUCCESS);
		} catch (Exception e) {
			log.error("合并应收房租失败（IncomeServiceImpl.addIncome）.......................", e);
		}
		return result;
	}
	@Override
	@Transactional
	public Result batchAddIncome(List<IncomeDTO> list, String companyId,Long createrId) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		if(list==null) {
			result.setMessage("应收房租数据不能为空");
			return result;
		}
		try {
			List<Income> inserts = new ArrayList<>(list.size());
			Income insert = new Income();
			for (IncomeDTO record : list) {
				BeanUtils.copyProperties(record, insert);
				insert.setCompanyId(companyId);
				insert.setCreaterId(createrId);
				insert.setCreateTime(new Date());
				inserts.add(insert);
			}
			incomeMapper.insertList(inserts);
			ResultUtil.setResult(result, RespCode.Code.SUCCESS);
		} catch (Exception e) {
			log.error("应收房租添加失败（IncomeServiceImpl.batchAddIncome）.......................", e);
		}
		return result;
	}

	@Override
	@Transactional
	public Result batchUpdateIncome(List<IncomeDTO> list, String companyId,Long createrId) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		if(list==null) {
			result.setMessage("应收房租数据不能为空");
			return result;
		}
		try {
			Income insert = new Income();
			for (IncomeDTO record : list) {
				BeanUtils.copyProperties(record, insert);
				if(insert.getId()==null) {
					result.setMessage("应收房租数据主键不能为空");
					return result;
				}
				insert.setCompanyId(companyId);
				insert.setUpdaterId(createrId);
				insert.setUpdateTime(new Date());
				incomeMapper.updateByPrimaryKeySelective(insert);
			}
			ResultUtil.setResult(result, RespCode.Code.SUCCESS);
		} catch (Exception e) {
			log.error("应收房租添加失败（IncomeServiceImpl.batchUpdateIncome）.......................", e);
		}
		return result;
	}
}
