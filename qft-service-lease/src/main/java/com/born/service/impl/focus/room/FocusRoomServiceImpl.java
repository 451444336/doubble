package com.born.service.impl.focus.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.facade.dto.focus.housing.UpdRoomCountHousingDTO;
import com.born.facade.dto.focus.room.FocusRoomDTO;
import com.born.facade.exception.focus.housing.FocusHousingException;
import com.born.facade.exception.focus.housing.FocusHousingExceptionEnum;
import com.born.facade.service.focus.room.IFocusRoomService;
import com.born.facade.vo.focus.room.FocusRoomVO;
import com.born.mapper.FocusHousingMapper;
import com.born.mapper.FocusRoomMapper;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(version = "1.0.0")
public class FocusRoomServiceImpl implements IFocusRoomService {

	@Autowired
	FocusRoomMapper focusRoomMapper;
	@Autowired
	FocusHousingMapper focusHousingMapper;

	@Override
	public Result batchAddOrUpdate(List<FocusRoomDTO> listDTO) {
		Result result = ResultUtil.fail();
		if(listDTO == null || listDTO.size() <= 0){
			return ResultUtil.fail(RespCode.Code.REQUEST_DATA_ERROR);
		}
		try {
			//分配房间
			log.info("执行添加或修改房间信息...");
			Integer returnFlag = 0;
			for(FocusRoomDTO dto : listDTO){
				returnFlag *= focusRoomMapper.insertOrUpdate(dto);
			}
			log.info("添加或修改房间信息成功...");
			
			//记录分配房间数量
			UpdRoomCountHousingDTO housingDto = new UpdRoomCountHousingDTO();
			housingDto.setVariableCount(listDTO.size());
			housingDto.setId(listDTO.get(0).getHousingId());
			log.info("执行修改房源信息中的房间数量...");
			returnFlag *= focusHousingMapper.updateRoomCount(housingDto);
			log.info("修改房源信息中的房间数量成功...");
			return ResultUtil.success(result, returnFlag);
		} catch (Exception e) {
			log.error("添加或修改房间信息失败（FocusRoomServiceImpl.add）-----------------"+e);
			throw new FocusHousingException(FocusHousingExceptionEnum.ADD_UPDATE_ROOM_ERROR);
		}
	}

	@Override
	public Result deleteById(Long id) {
		Result result = ResultUtil.fail();
		try {
			log.info("执行删除房间...");
			Integer returnFlag = focusRoomMapper.deleteById(id);
			log.info("删除房间成功...");
			return ResultUtil.success(result, returnFlag);
		} catch (Exception e) {
			log.error("删除房间失败（FocusRoomServiceImpl.deleteById）-----------------"+e);
			throw new FocusHousingException(FocusHousingExceptionEnum.DELETE_ROOM_ERROR);
		}
	}

	@Override
	public Result getRoomList(FocusRoomDTO dto) {
		Result result = ResultUtil.fail();
		try {
			log.info("执行查询房间列表...");
			List<FocusRoomVO> list = focusRoomMapper.selectRoomList(dto);
			log.info("查询房间列表成功...");
			PageInfo<FocusRoomVO> pageInfo = new PageInfo<>(list);
	        result.setData(pageInfo.getList());
			result.setCount(pageInfo.getTotal());
			return ResultUtil.success(result, list);
		} catch (Exception e) {
			log.error("查询房间列表失败（FocusRoomServiceImpl.getRoomList）-----------------"+e);
		}
		return result;
	}

}
