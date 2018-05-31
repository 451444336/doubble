package com.born.service.impl.focus.housing;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.facade.dto.focus.housing.AddIrregularDTO;
import com.born.facade.dto.focus.housing.AddOrUpdHousingDTO;
import com.born.facade.dto.focus.housing.FocusHousingDTO;
import com.born.facade.dto.focus.housing.FocusTrustDTO;
import com.born.facade.dto.focus.housing.UpdRoomCountHousingDTO;
import com.born.facade.exception.focus.housing.FocusHousingException;
import com.born.facade.exception.focus.housing.FocusHousingExceptionEnum;
import com.born.facade.service.focus.housing.IFocusHousingService;
import com.born.facade.vo.focus.housing.AddIrregularVO;
import com.born.facade.vo.focus.housing.FocusHousingVO;
import com.born.mapper.AddIrregularMapper;
import com.born.mapper.FocusHousingMapper;
import com.born.mapper.FocusTrustMapper;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @description 集中整租房源ServiceImpl
 * @author 黄伟
 * @date 2018年5月28日 下午3:57:06
 */
@Slf4j
@Service(version = "1.0.0")
public class FocusHousingServiceImpl implements IFocusHousingService {

	@Autowired
	private FocusHousingMapper focusHousingMapper;
	@Autowired
	private FocusTrustMapper focusTrustMapper;
	@Autowired
	private AddIrregularMapper addIrregularMapper;
	
	@Override
	@Transactional
	public Result addOrUpdate(AddOrUpdHousingDTO dto) {
		Result result = ResultUtil.fail();
		//验证参数
		String errorStr = dto.validateForm();
		if (StringUtils.isNotBlank(errorStr)) {
			result.setMessage(errorStr);
			return result;
		}
		try {
			//添加或修改集中整租房源信息
			log.info("执行添加或修改集中整租房源信息...");
			Integer returnFlag = focusHousingMapper.insertOrUpdate(dto);
			log.info("添加或修改集中整租房源信息成功...");
			ResultUtil.success(result, returnFlag);
			//如果是自持，则返回；如果是租赁，则添加托管信息
			if(dto.getFocusType() == 0){
				return result;
			}
			//添加房源托管信息
			FocusTrustDTO trustDTO = new FocusTrustDTO();
			trustDTO.setHousingId(dto.getId());
			BeanUtils.copyProperties(dto, trustDTO);
			//添加房源托管信息
			log.info("执行添加或修改集中整租房源托管信息...");
			returnFlag *= focusTrustMapper.insertOrUpdate(trustDTO);
			log.info("添加或修改集中整租房源托管信息成功...");
			ResultUtil.success(result, returnFlag);
			//无递增或有递增但不为不规则递增，则返回
			if(dto.getAddAppoint() == 0 || (dto.getAddAppoint() == 1 && dto.getAddType() != 2)){
				return result;
			}
			//添加或修改集中整租房源托管不规则递增信息
			List<AddIrregularDTO> list = dto.getList();
			log.info("执行添加或修改集中整租房源托管不规则递增信息...");
			for(AddIrregularDTO addIrregularDTO : list){
				addIrregularDTO.setTrustId(trustDTO.getId());
				returnFlag *= addIrregularMapper.insertOrUpdate(addIrregularDTO);
			}
			log.info("添加集中或修改整租房源托管不规则递增信息成功...");
			
			ResultUtil.success(result, returnFlag);
			
		} catch (Exception e) {
			log.error("添加或修改集中整租房源失败（FocusHousingServiceImpl.addOrUpdate）-----------------------------"+e);
			throw new FocusHousingException(FocusHousingExceptionEnum.ADD_UPDATE_HOUSING_ERROR);
		}
		return result;
	}

	@Override
	public Result updateRoomCount(UpdRoomCountHousingDTO dto) {
		Result result = ResultUtil.fail();
		if(dto.getId() == null ||dto.getId() == 0 
				|| (dto.getVariableCount() == 0 && dto.getVariableRestCount() == 0)){
			return ResultUtil.fail(RespCode.Code.REQUEST_DATA_ERROR);
		}
		try {
			Integer returnFlag = focusHousingMapper.updateRoomCount(dto);
			return ResultUtil.success(result,returnFlag);
		} catch (Exception e) {
			log.error("添加或修改集中整租房源失败（FocusHousingServiceImpl.addOrUpdate）-----------------------------"+e);
			throw new FocusHousingException(FocusHousingExceptionEnum.UPDATE_ROOM_COUNT_ERROR);
		}
	}
	
	@Override
	public Result deleteById(Long id) {
		Result result = ResultUtil.fail();
		//验证参数
		if(id == null){
			return ResultUtil.fail(RespCode.Code.REQUEST_DATA_ERROR);
		}
		try {
			//根据ID删除房源信息
			log.info("执行删除集中整租房源信息...");
			Integer returnFlag = focusHousingMapper.deleteById(id);
			log.info("删除集中整租房源信息成功...");
			return ResultUtil.success(result, returnFlag);
		} catch (Exception e) {
			log.error("删除集中整租房源失败（FocusHousingServiceImpl.deleteById）-----------------------------"+e);
			throw new FocusHousingException(FocusHousingExceptionEnum.DELETE_HOUSING_ERROR);
		}
	}
	
	@Override
	public Result getHousingById(Long id) {
		Result result = ResultUtil.fail();
		//验证参数
		if(id == null){
			return ResultUtil.fail(RespCode.Code.REQUEST_DATA_ERROR);
		}
		try {
			//根据ID查询房源信息
			log.info("执行根据ID查询集中整租房源信息...");
			ResultUtil.success(result, focusHousingMapper.selectHousingById(id));
			log.info("根据ID集中整租房源信息成功...");
		} catch (Exception e) {
			log.error("集中整租根据ID房源信息失败（FocusHousingServiceImpl.getHousingOne）-----------------------------"+e);
			throw new FocusHousingException(FocusHousingExceptionEnum.DELETE_HOUSING_ERROR);
		}
		return result;
	}
	
	@Override
	public Result getHousingList(FocusHousingDTO dto) {
		Result result = ResultUtil.fail();
		try {
			PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
			//获取房源信息列表
			log.info("执行查询集中整租房源信息列表...");
			List<FocusHousingVO> list = focusHousingMapper.selectHousingList(dto);
			log.info("查询集中整租房源信息列表成功...");
			PageInfo<FocusHousingVO> pageInfo = new PageInfo<>(list);
	        result.setData(pageInfo.getList());
			result.setCount(pageInfo.getTotal());
			return ResultUtil.success(result, list);
		} catch (Exception e) {
			log.error("查询集中整租房源列表信息失败（FocusHousingServiceImpl.getHousingList）-----------------------------"+e);
		}
		return result;
	}

	@Override
	public Result getAddIrregularAll(Long trustId) {
		Result result = ResultUtil.fail();
		try {
			//根据托管ID获取不规则递增信息
			List<AddIrregularVO> list = addIrregularMapper.getAddIrregularAll(trustId);
			ResultUtil.success(result, list);
		} catch (Exception e) {
			log.error("查询不规则递增信息失败（FocusHousingServiceImpl.getAddIrregularAll）-----------------------------"+e);
		}
		return result;
	}

}
