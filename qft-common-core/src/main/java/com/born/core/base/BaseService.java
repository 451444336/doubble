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
		final DataBaseParameters<E> dataBase = getDataBaseParameters();
		Assert.notNull(dataBase, "execute init method data base parameters is null");
		Assert.notNull(dataBase.getEntityClass(), "execute init method  entity class is null");
		Assert.notNull(dataBase.getMapper(), "execute init method  mapper is null");
		entityClass = dataBase.getEntityClass();
		mapper = dataBase.getMapper();
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
				return ResultUtil.fail(RespCode.Code.REQUEST_DATA_ERROR, errorStr);
			}
			E update = entityClass.newInstance();
			BeanUtils.copyProperties(record, update);
			final int r = mapper.updateByPrimaryKeySelective(update);
			if (r > 0) {
				return ResultUtil.success(r);
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
				return ResultUtil.success(r);
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
				return ResultUtil.success(r);
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
				return ResultUtil.success(r);
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
				return ResultUtil.success(r);
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
				return ResultUtil.fail(RespCode.Code.REQUEST_DATA_ERROR, errorStr);
			}
			E insert = entityClass.newInstance();
			BeanUtils.copyProperties(record, insert);
			final int r = mapper.insertUseGeneratedKeys(insert);
			if (r > 0) {
				return ResultUtil.success(ClassUtils.getProValue("id", insert));
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
				return ResultUtil.fail(RespCode.Code.REQUEST_DATA_ERROR, errorStr);
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
				return ResultUtil.success(r);
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
		Result result = ResultUtil.fail();
		try {
			E data = mapper.selectByPrimaryKey(id);
			return ResultUtil.success(result, BeanMapUtils.beanToMap(data));
		} catch (Exception e) {
			log.error("get data by id error", e);
		}
		return result;
	}

	@Override
	public Result getByIdsList(List<Long> ids) {
		if (log.isInfoEnabled()) {
			log.info("get data By ids request data = {}", ids);
		}
		Result result = ResultUtil.fail();
		try {
			Example example = new Example(entityClass);
			Criteria criteria = example.createCriteria();
			criteria.andIn("id", ids);
			List<E> list = mapper.selectByExample(example);
			return ResultUtil.success(result, BeanMapUtils.objectsToMaps(list));
		} catch (Exception e) {
			log.error("get data by ids error", e);
		}
		return result;
	}

	@Override
	public Result getByModelList(T entity) {
		if (log.isInfoEnabled()) {
			log.info("get data By entity list request data = {}", JSON.toJSONString(entity));
		}
		Result result = ResultUtil.fail();
		try {
			E record = entityClass.newInstance();
			BeanUtils.copyProperties(entity, record);
			List<E> list = mapper.select(record);
			return ResultUtil.success(result, BeanMapUtils.objectsToMaps(list));
		} catch (Exception e) {
			log.error("get data By entity list error", e);
		}
		return result;
	}

	@Override
	public Result getByModelOne(T entity) {
		if (log.isInfoEnabled()) {
			log.info("get data By entity one request data = {}", JSON.toJSONString(entity));
		}
		Result result = ResultUtil.fail();
		try {
			E record = entityClass.newInstance();
			BeanUtils.copyProperties(entity, record);
			record = mapper.selectOne(record);
			return ResultUtil.success(result, BeanMapUtils.beanToMap(record));
		} catch (Exception e) {
			log.error("get data By entity one error", e);
		}
		return result;
	}
	
	@Override
	public Result getAll() {
		try {
			return ResultUtil.success(BeanMapUtils.objectsToMaps(mapper.selectAll()));
		} catch (Exception e) {
			log.error("get data By entity one error", e);
			return ResultUtil.fail();
		}
	}
}
