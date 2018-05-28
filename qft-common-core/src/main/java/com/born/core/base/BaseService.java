package com.born.core.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.born.core.constant.DataBaseEnum;
import com.born.core.exception.BizException;
import com.born.core.exception.DataBaseException;
import com.born.util.ClassUtils;
import com.born.util.bean.BeanMapUtils;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;

import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 
 * @ClassName: BaseService
 * @Description: 抽象共用服务
 * @author: lijie
 * @date: 2018年5月26日 下午4:48:55
 * @param <T>
 */
@Slf4j
public abstract class BaseService<T extends BaseModel, E> implements IBaseService<T> {

	private final RespCode.Code ERROR_CODE = RespCode.Code.INTERNAL_SERVER_ERROR;

	private volatile Class<E> entityClass;

	private volatile BaseMapper<E> mapper;

	/**
	 * 
	* @Title: initDataBaseParameters  
	* @Description: 数据操作mapper 
	* @param: @return
	* @return BaseMapper<?>
	* @author lijie
	* @throws
	 */
	protected abstract DataBaseParameters<E> getDataBaseParameters();
	/**
	 * 
	* @Title: init  
	* @Description: 实现初始化校验操作
	* @param: 
	* @return void
	* @author lijie
	* @throws
	 */
	@PostConstruct
	private void init() {
		Assert.notNull(getDataBaseParameters(), "execute init method data base parameters is null");
		Assert.notNull(getDataBaseParameters().getEntityClass(), "execute init method  entity class is null");
		Assert.notNull(getDataBaseParameters().getMapper(), "execute init method  mapper is null");
		entityClass = getDataBaseParameters().getEntityClass();
		mapper = getDataBaseParameters().getMapper();
	}
	
	@Override
	@Transactional
	public Result updateByModel(T record) {
		if (log.isInfoEnabled()) {
			log.info("update by model request data = {}", JSON.toJSONString(record));
		}
		try {
			String errorStr = record.validateForm();
			if (StringUtils.isNotBlank(errorStr)) {
				return ResultUtil.getResult(RespCode.Code.REQUEST_DATA_ERROR, errorStr);
			}
			E update = entityClass.newInstance();
			BeanUtils.copyProperties(record, update);
			final int r = mapper.updateByPrimaryKeySelective(update);
			if (r > 0) {
				return ResultUtil.getResult(RespCode.Code.SUCCESS, r);
			} else {
				throw new DataBaseException(String.format("update by model fail [success num : %s]", r));
			}
		} catch (Exception e) {
			log.error("update by model error", e);
			throw new BizException(ERROR_CODE.getMsg(), ERROR_CODE.getCode());
		}
	}

	@Override
	@Transactional
	public Result delByIds(List<Long> ids, Long userId) {
		if (log.isInfoEnabled()) {
			log.info("del by ids request data ids = {},userId={}", JSON.toJSONString(ids), userId);
		}
		try {
			final int r = execDelByLogic(ids, userId);
			if (r > 0) {
				return ResultUtil.getResult(RespCode.Code.SUCCESS, r);
			} else {
				throw new DataBaseException(String.format("del by ids fail [success num : %s]", r));
			}
		} catch (Exception e) {
			log.error("del by ids error", e);
			throw new BizException(ERROR_CODE.getMsg(), ERROR_CODE.getCode());
		}
	}
	/**
	 * 
	* @Title: execDelByLogic  
	* @Description: 逻辑删除数据 
	* @param: @param ids
	* @param: @param userId
	* @param: @return
	* @param: @throws InstantiationException
	* @param: @throws IllegalAccessException
	* @return int
	* @author lijie
	* @throws
	 */
	private int execDelByLogic(List<Long> ids, Long userId) throws InstantiationException, IllegalAccessException {
		E record = entityClass.newInstance();
		Example example = new Example(record.getClass());
		Criteria criteria = example.createCriteria();
		criteria.andIn("id", ids);
		final Map<String, Object> map = new HashMap<>();
		map.put("isDelete", DataBaseEnum.DELETE.getStatus());
		map.put("updateTime", new Date());
		map.put("updaterId", userId);
		ClassUtils.setProValue(record, map);
		return mapper.updateByExampleSelective(record, example);
	}
	
	@Override
	@Transactional
	public Result delByIds(List<Long> ids) {
		if (log.isInfoEnabled()) {
			log.info("del by ids request data ids = {}", JSON.toJSONString(ids));
		}
		try {
			Example example = new Example(entityClass);
			Criteria criteria = example.createCriteria();
			criteria.andIn("id", ids);
			final int r = mapper.deleteByExample(example);
			if (r > 0) {
				return ResultUtil.getResult(RespCode.Code.SUCCESS, r);
			} else {
				throw new DataBaseException(String.format("del by ids fail [success num : %s]", r));
			}
		} catch (Exception e) {
			log.error("del by ids error", e);
			throw new BizException(ERROR_CODE.getMsg(), ERROR_CODE.getCode());
		}
	}

	@Override
	@Transactional
	public Result delById(Long id, Long userId) {
		if (log.isInfoEnabled()) {
			log.info("del by id request data id = {},userId = {}", id, userId);
		}
		try {
			List<Long> ids = new ArrayList<>(1);
			ids.add(id);
			final int r = execDelByLogic(ids, userId);
			if (r > 0) {
				return ResultUtil.getResult(RespCode.Code.SUCCESS, r);
			} else {
				throw new DataBaseException(String.format("del by id fail [success num : %s]", r));
			}
		} catch (Exception e) {
			log.error("del by id error", e);
			throw new BizException(ERROR_CODE.getMsg(), ERROR_CODE.getCode());
		}
	}

	@Override
	@Transactional
	public Result delById(Long id) {
		if (log.isInfoEnabled()) {
			log.info("del by id request data id = {}", id);
		}
		try {
			final int r = mapper.deleteByPrimaryKey(id);
			if (r > 0) {
				return ResultUtil.getResult(RespCode.Code.SUCCESS, r);
			} else {
				throw new DataBaseException(String.format("del by ids fail [success num : %s]", r));
			}
		} catch (Exception e) {
			log.error("del by id error", e);
			throw new BizException(ERROR_CODE.getMsg(), ERROR_CODE.getCode());
		}
	}

	@Override
	@Transactional
	public Result addByModel(T record) {
		if (log.isInfoEnabled()) {
			log.info("add by model request data = {}", JSON.toJSONString(record));
		}
		try {
			String errorStr = record.validateForm();
			if (StringUtils.isNotBlank(errorStr)) {
				return ResultUtil.getResult(RespCode.Code.REQUEST_DATA_ERROR, errorStr);
			}
			E insert = entityClass.newInstance();
			BeanUtils.copyProperties(record, insert);
			final int r = mapper.insertSelective(insert);
			if (r > 0) {
				return ResultUtil.getResult(RespCode.Code.SUCCESS, r);
			} else {
				throw new DataBaseException(String.format("update by model fail [success num : %s]", r));
			}
		} catch (Exception e) {
			log.error("add by model error", e);
			throw new BizException(ERROR_CODE.getMsg(), ERROR_CODE.getCode());
		}
	}

	@Override
	@Transactional
	public Result addByList(List<T> records) {
		if (log.isInfoEnabled()) {
			log.info("add by list request data = {}", JSON.toJSONString(records));
		}
		try {
			String errorStr = ClassUtils.checkRequest(records);
			if (StringUtils.isNotBlank(errorStr)) {
				return ResultUtil.getResult(RespCode.Code.REQUEST_DATA_ERROR, errorStr);
			}
			final List<E> inserts = new ArrayList<>(records.size());
			E insert;
			for (T record : records) {
				insert = entityClass.newInstance();
				BeanUtils.copyProperties(record, insert);
				inserts.add(insert);
			}
			final int r = mapper.insertList(inserts);
			if (r > 0) {
				return ResultUtil.getResult(RespCode.Code.SUCCESS, r);
			} else {
				throw new DataBaseException(String.format("add by list fail [success num : %s]", r));
			}
		} catch (Exception e) {
			log.error("add by list error", e);
			throw new BizException(ERROR_CODE.getMsg(), ERROR_CODE.getCode());
		}
	}
	
	@Override
	public Result getById(Long id) {
		if (log.isInfoEnabled()) {
			log.info("get data By Id request data = {}", id);
		}
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		try {
			final DataBaseParameters<E> exec = getDataBaseParameters();
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS,
					BeanMapUtils.beanToMap(exec.getMapper().selectByPrimaryKey(id)));
		} catch (Exception e) {
			log.error("get data by id error", e);
		}
		return result;
	}

	@Override
	public Result getByIdsList(List<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result getByEntityList(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result getByEntityOne(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Result updateByEntity(T record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Result batchUpdateByEntity(List<T> entityList) {
		// TODO Auto-generated method stub
		return null;
	}
}
