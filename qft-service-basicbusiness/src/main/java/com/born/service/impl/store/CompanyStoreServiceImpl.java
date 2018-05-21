package com.born.service.impl.store;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.facade.dto.store.CompanyStoreDTO;
import com.born.facade.dto.store.StoreGroupDTO;
import com.born.facade.entity.store.CompanyStore;
import com.born.facade.entity.store.StoreGroup;
import com.born.facade.service.store.ICompanyStoreService;
import com.born.facade.vo.store.CompanyStoreVO;
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
public class CompanyStoreServiceImpl implements ICompanyStoreService {

	@Autowired
	private CompanyStoreMapper companyStoreMapper;
	@Autowired
	private StoreGroupMapper storeGroupMapper;
	
	@Override
	public Result insert(CompanyStoreDTO dto) {
		// 验证参数
		if (StringUtils.isBlank(dto.getCity()) || StringUtils.isBlank(dto.getName())) {
			
			return ResultUtil.getResult(RespCode.Code.REQUEST_DATA_ERROR);
		}
		//添加店面
		Long id =companyStoreMapper.insertCompanyStore(dto);
		//组装店面分组信息
		StoreGroup group = new StoreGroup();
		group.setCompanyId(dto.getCompanyId());
		group.setCreaterId(dto.getCreaterId());
		group.setCreateTime(dto.getCreateTime());
		group.setUpdaterId(dto.getUpdaterId());
		group.setUpdateTime(dto.getUpdateTime());
		group.setStoreId(dto.getId());
		if(dto.getGroupingStatus() == 0){
			String[] groupName = dto.getGrouping().split(",");
			for(String name : groupName){
				group.setName(name);
				//添加店面分组
				storeGroupMapper.insertSelective(group);
			}

		}
		// 保存数据
		log.info("执行保存操作...");
		Result result = ResultUtil.getResult(RespCode.Code.SUCCESS, 1);
		log.info("保存操作成功...");
		return result;
	}
	
	@Override
	public Result update(CompanyStoreDTO dto) {
		// 验证参数
		if (dto.getId() == null) {
			return ResultUtil.getResult(RespCode.Code.REQUEST_DATA_ERROR, dto);
		}
		// 查询店面信息
		CompanyStore store = companyStoreMapper.selectByPrimaryKey(dto.getId());
		if(store == null) {
			return ResultUtil.getResult(RespCode.Code.NOT_QUERY_DATA);
		}
		BeanUtils.copyProperties(dto, store);
		// 保存数据
		log.info("执行修改操作...");
		Result result = ResultUtil.getResult(RespCode.Code.SUCCESS, companyStoreMapper.updateByPrimaryKeySelective(store));
		log.info("修改操作成功...");
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
		log.info("执行删除操作...");
		//删除店面
		companyStoreMapper.deleteStoreById(id);
		log.info("删除操作成功...");
		return ResultUtil.getResult(RespCode.Code.SUCCESS);
	}
	
	@Override
	public Result getStoreById(CompanyStoreDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		// 验证参数
		if (dto.getId() == null ) {
			result.setMessage("主键不能为空");
			return result;
		}
		try {
			ResultUtil.setResult(result, RespCode.Code.SUCCESS, companyStoreMapper.selectCompanyStore(dto));
		} catch (Exception e) {
			log.error("查询店面失败(CompanyStoreServiceImpl.getStoreById).......................", e);
		}
		
		return result;
	}
	@Override
	public Result getPageList(CompanyStoreDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		try {
		 	PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		 	//获取店面列表
	        List<CompanyStoreVO> list = companyStoreMapper.selectCompanyStore(dto);
	        for(CompanyStoreVO vo : list){
	        	String grouping = "";
	        	//根据店面ID获取对应的分组列表
	        	StoreGroupDTO groupDto = new StoreGroupDTO();
	        	groupDto.setStoreId(vo.getId());
	        	List<StoreGroupVO> groupList= storeGroupMapper.selectGroupListByStoreId(groupDto);
	        	for(StoreGroupVO groupVO : groupList){
	        		grouping += groupVO.getName() + ",";
	        	}
	        	if(groupList.size()>0){
	        		grouping += "（共" + groupList.size() + "组）";
	        	}
	        	vo.setGrouping(grouping);
	        }
	        PageInfo<CompanyStoreVO> pageInfo = new PageInfo<>(list);
	        result.setData(pageInfo.getList());
			result.setCount(pageInfo.getTotal());
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS);
		} catch (Exception e) {
			log.error("查询分页数据异常", e);
		}
		return result;
	}
}
