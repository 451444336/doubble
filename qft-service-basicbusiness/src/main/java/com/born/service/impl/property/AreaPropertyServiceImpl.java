package com.born.service.impl.property;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.entity.property.AreaProperty;
import com.born.facade.dto.property.AreaPropertyDTO;
import com.born.facade.dto.property.ChangeAreaPropertyDTO;
import com.born.facade.exception.AreaPropertyExceptionEnum;
import com.born.facade.exception.BasicBusinessException;
import com.born.facade.service.property.IAreaPropertyService;
import com.born.facade.vo.property.AreaPropertyVO;
import com.born.facade.vo.property.ProvCityAreaVO;
import com.born.mapper.AreaPropertyMapper;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;
import com.esotericsoftware.minlog.Log;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(version = "1.0.0")
public class AreaPropertyServiceImpl implements IAreaPropertyService {
	
	@Autowired
	AreaPropertyMapper areaPropertyMapper;

	@Override
	public Result add(ChangeAreaPropertyDTO dto) {
		Result result = ResultUtil.fail();
		String errorStr = dto.validateForm();
		if(StringUtils.isNotBlank(errorStr)){
			result.setMessage(errorStr);
			return result;
		}
		try {
			//添加楼盘地址
			AreaProperty property = new AreaProperty();
			BeanUtils.copyProperties(dto, property);
			log.info("执行添加楼盘地址...");
			Integer res = areaPropertyMapper.insert(property);
			log.info("添加楼盘地址成...");
			return ResultUtil.success(result, res);
		} catch (Exception e) {
			Log.error("添加楼盘地址失败（AreaPropertyServiceImpl.add）--------------"+e);
			throw new BasicBusinessException(AreaPropertyExceptionEnum.ADD_AREA_PROPERTY);
		}
	}

	@Override
	public Result update(ChangeAreaPropertyDTO dto) {
		Result result = ResultUtil.fail();
		String errorStr = dto.validateForm();
		if(StringUtils.isNotBlank(errorStr)){
			result.setMessage(errorStr);
			return result;
		}
		try {
			//根据楼盘ID查询楼盘信息
			AreaProperty property = areaPropertyMapper.selectByPrimaryKey(dto.getId());
			BeanUtils.copyProperties(dto, property);
			//修改楼盘地址
			log.info("执行修改楼盘地址...");
			Integer res = areaPropertyMapper.updateByPrimaryKeySelective(property);
			log.info("修改楼盘地址成功...");
			return ResultUtil.success(result, res);
		} catch (Exception e) {
			Log.error("修改楼盘地址失败（AreaPropertyServiceImpl.update）--------------"+e);
			throw new BasicBusinessException(AreaPropertyExceptionEnum.UPDATE_AREA_PROPERTY);
		}
	}

	@Override
	public Result delete(Long id) {
		Result result = ResultUtil.fail();
		try {
			//删除楼盘地址
			log.info("执行删除楼盘地址...");
			Integer res = areaPropertyMapper.deleteById(id);
			log.info("删除楼盘地址成功...");
			return ResultUtil.success(result, res);
		} catch (Exception e) {
			Log.error("删除楼盘地址失败（AreaPropertyServiceImpl.delete）--------------"+e);
			throw new BasicBusinessException(AreaPropertyExceptionEnum.DELETE_AREA_PROPERTY);
		}
	}

	@Override
	public Result getProCityAreaAll(String areaName) {
		Result result = ResultUtil.fail();
		try {
			//根据区域地址获取下级区域
			log.info("执行根据区域名称查询下级区域...");
			List<ProvCityAreaVO> list = areaPropertyMapper.seleteProCityArea(areaName);
			log.info("根据区域名称查询下级区域成功...");
			return ResultUtil.success(result, list);
		} catch (Exception e) {
			Log.error("根据区域名称查询下级区域失败（AreaPropertyServiceImpl.getProCityArea）--------------"+e);
		}
		return result;
	}

	@Override
	public Result getAreaPropertyAll(AreaPropertyDTO dto) {
		Result result = ResultUtil.fail();
		try {
			//根据区域地址获取下级区域
			log.info("执行根据区域名称查询下级区域...");
			List<ProvCityAreaVO> list = areaPropertyMapper.seleteProCityArea(dto.getAreaName());
			log.info("根据区域名称查询下级区域成功...");
			//根据区县查询楼盘地址
			for(ProvCityAreaVO area : list){
				AreaPropertyDTO propertyDTO = new AreaPropertyDTO();
				propertyDTO.setCompanyId(dto.getCompanyId());
				propertyDTO.setAreaName(area.getAreaName());
				log.info("执行根据区县查询楼盘地址...");
				List<AreaPropertyVO> propertyList = areaPropertyMapper.seleteAreaProperty(propertyDTO);
				log.info("根据区县查询楼盘地址成功...");
				area.setList(propertyList);
			}
			return ResultUtil.success(result, list);
		} catch (Exception e) {
			Log.error("根据区域名称查询楼盘地址失败（AreaPropertyServiceImpl.add）--------------"+e);
		}
		return result;
	}

}
