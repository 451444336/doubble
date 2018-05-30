package com.born.service.impl.focus.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.facade.dto.focus.room.RoomConfigDTO;
import com.born.facade.exception.focus.housing.FocusHousingException;
import com.born.facade.exception.focus.housing.FocusHousingExceptionEnum;
import com.born.facade.service.focus.room.IRoomConfigService;
import com.born.facade.vo.focus.room.RoomConfigVO;
import com.born.mapper.RoomConfigMapper;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(version = "1.0.0")
public class RoomConfigServiceImpl implements IRoomConfigService {

	@Autowired
	RoomConfigMapper roomConfigMapper;
	
	@Override
	public Result batchAddOrUpdate(List<RoomConfigDTO> list) {
		Result result = ResultUtil.fail();
		if(list ==null || list.size() <= 0){
			return ResultUtil.fail(RespCode.Code.REQUEST_DATA_ERROR);
		}
		try {
			Integer returnFlag = 0;
			for(RoomConfigDTO dto : list){
				//添加或修改房间配置信息
				log.info("执行添加或修改房间配置信息...");
				returnFlag *= roomConfigMapper.insertOrUpdate(dto);
				log.info("添加或修改房间配置信息成功...");
			}
			
			return ResultUtil.success(result, returnFlag);
		} catch (Exception e) {
			log.error("添加或修改房间配置信息失败（RoomConfigServiceImpl.addOrUpdate）-----------"+e);
			throw new FocusHousingException(FocusHousingExceptionEnum.ADD_UPDATE_CONFIG_ERROR);
		}
	}

	@Override
	public Result deleteById(Long id) {
		Result result = ResultUtil.fail();
		if(id == null){
			return ResultUtil.fail(RespCode.Code.REQUEST_DATA_ERROR);
		}
		try {
			//根据房间配置ID删除房间配置信息
			log.info("执行根据房间配置ID删除房间配置信息...");
			Integer returnFlag = roomConfigMapper.deleteById(id);
			log.info("根据房间配置ID删除房间配置信息成功...");
			return ResultUtil.success(result, returnFlag);
		} catch (Exception e) {
			log.error("根据房间配置ID删除房间配置信息失败（RoomConfigServiceImpl.deleteById）-----------"+e);
			throw new FocusHousingException(FocusHousingExceptionEnum.DELETE_CONFIG_ERROR);
		}
	}

	@Override
	public Result getConfigByParentId(Long parentId) {
		Result result = ResultUtil.fail();
		if(parentId == null){
			return ResultUtil.fail(RespCode.Code.REQUEST_DATA_ERROR);
		}
		try {
			//根据父级ID查询房间配置信息
			log.info("执行根据父级ID查询房间配置信息...");
			List<RoomConfigVO> list= roomConfigMapper.selectConfigByParentId(parentId);
			log.info("根据父级ID查询房间配置信息成功...");
			return ResultUtil.success(result, list);
		} catch (Exception e) {
			log.error("根据父级ID查询房间配置信息失败（RoomConfigServiceImpl.getConfigByParentId）-----------"+e);
		}
		return result;
	}

	@Override
	public Result getConfigAll(RoomConfigDTO dto) {
		Result result = ResultUtil.fail();
		try {
			//查询房间配置信息
			log.info("执行查询房间配置信息...");
			List<RoomConfigVO> list= roomConfigMapper.selectConfigAll(dto);
			log.info("查询房间配置信息成功...");
			return ResultUtil.success(result, list);
		} catch (Exception e) {
			log.error("查询房间配置信息失败（RoomConfigServiceImpl.getConfigAll）-----------"+e);
			throw new FocusHousingException(FocusHousingExceptionEnum.DELETE_CONFIG_ERROR);
		}
	}

}
