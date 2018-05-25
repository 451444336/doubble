package com.born.service.impl.store;


import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.entity.store.CompanyStore;
import com.born.entity.store.StoreGroup;
import com.born.facade.dto.store.StoreGroupDTO;
import com.born.facade.exception.StoreException;
import com.born.facade.exception.StoreExceptionEnum;
import com.born.facade.service.store.IStoreGroupService;
import com.born.facade.vo.store.StoreGroupVO;
import com.born.mapper.CompanyStoreMapper;
import com.born.mapper.StoreGroupMapper;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 店面管理
 * @author 黄伟
 * @date 2018年5月15日 下午2:24:25
 */
@Slf4j
@Service(version = "1.0.0")
public class StoreGroupServiceImpl implements IStoreGroupService {

	@Autowired
	private StoreGroupMapper storeGroupMapper;
	@Autowired
	private CompanyStoreMapper companyStoreMapper;
	
	@Override
	@Transactional
	public Result batchAdd(List<StoreGroupDTO> list) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		// 验证参数
		if (list.size()<0) {
			
			return ResultUtil.getResult(RespCode.Code.REQUEST_DATA_ERROR);
		}
		try {
			Long storeId = 0L;
			Long updaterId = 0L;
			try {
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			//批量添加分组
			StoreGroup group = new StoreGroup();
			for(StoreGroupDTO dto : list){
				storeId = dto.getStoreId();
				updaterId = dto.getUpdaterId();
				BeanUtils.copyProperties(dto, group);
				storeGroupMapper.insertSelective(group);
			}
			//修改店面分组状态为0分组
			CompanyStore store = new CompanyStore();
			store.setId(storeId);
			store.setGroupingStatus((byte)0);
			store.setUpdaterId(updaterId);
			store.setUpdateTime(new Date());
			companyStoreMapper.updateByPrimaryKeySelective(store);
			log.info("执行保存操作...");
			result = ResultUtil.getResult(RespCode.Code.SUCCESS, 1);
			log.info("保存操作成功...");
		} catch (Exception e) {
			log.error("批量添加店面分组失败（StoreGroupServiceImpl.insertBatch）.......................",e);
			throw new StoreException(StoreExceptionEnum.ADD_GROUP_ERROR);
		}
		return result;
	}
	
	@Override
	@Transactional
	public Result update(StoreGroupDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		// 验证参数
		if (dto.getId() == null) {
			return ResultUtil.getResult(RespCode.Code.REQUEST_DATA_ERROR, dto);
		}
		try {
			// 查询店面分组信息
			StoreGroup group = storeGroupMapper.selectByPrimaryKey(dto.getId());
			if(group == null) {
				return ResultUtil.getResult(RespCode.Code.NOT_QUERY_DATA);
			}
			BeanUtils.copyProperties(dto, group);
			// 保存数据
			log.info("执行修改操作...");
			ResultUtil.setResult(result,RespCode.Code.SUCCESS, storeGroupMapper.updateByPrimaryKeySelective(group));
			log.info("修改操作成功...");
		} catch (Exception e) {
			log.error("修改店面分组失败（StoreGroupServiceImpl.update）......................."+e);
			throw new StoreException(StoreExceptionEnum.UPDATE_GROUP_ERROR);
		}
		
		return result;
	}
	
	@Override
	public Result deleteById(Long id) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		// 验证参数
		if (id == null ) {
			result.setMessage("主键不能为空");
			return result;
		}
		try {
			log.info("执行删除操作...");
			//删除店面
			storeGroupMapper.deleteGroupById(id);
			log.info("删除操作成功...");
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS);
		} catch (Exception e) {
			log.error("删除店面分组失败（StoreGroupServiceImpl.deleteById）......................."+e);
			throw new StoreException(StoreExceptionEnum.DELETE_GROUP_ERROR);
		}
	}
	
	@Override
	public Result getGroupByStoreId(StoreGroupDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		// 验证参数
		if (dto.getStoreId() == null) {
			return ResultUtil.getResult(RespCode.Code.REQUEST_DATA_ERROR, dto);
		}
		try {
		 	PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		 	//获取店面列表
	        List<StoreGroupVO> list = storeGroupMapper.selectGroupByStoreId(dto);
	        PageInfo<StoreGroupVO> pageInfo = new PageInfo<>(list);
	        result.setData(pageInfo.getList());
			result.setCount(pageInfo.getTotal());
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS);
		} catch (Exception e) {
			log.error("查询店面分组失败（StoreGroupServiceImpl.deleteById）......................."+e);
		}
		return result;
	}
}
