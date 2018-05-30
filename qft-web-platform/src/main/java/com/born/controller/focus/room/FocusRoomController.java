/**
 * 
 */
package com.born.controller.focus.room;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.born.config.shiro.token.TokenManager;
import com.born.facade.dto.focus.room.FocusRoomDTO;
import com.born.facade.service.focus.room.IFocusRoomService;
import com.born.facade.vo.UserInfoVO;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @description 房间配置控制类
 * @author 黄伟
 * @date 2018年5月29日 下午4:11:05
 */
@Controller
@RequestMapping(value = "/web/room")
public class FocusRoomController {

	@Reference(version = "1.0.0")
	IFocusRoomService focusRoomService;

	/**
	 * 
	 * @Title
	 * @param 房间配置dto
	 * @Description 添加房间配置
	 * @author 黄伟
	 * @return
	 * @date 2018年5月29日 下午2:04:04
	 */
	@ApiOperation(value = "添加房间配置",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@PostMapping(value = "/add")
	@ResponseBody
	public Result addRoom(@RequestBody List<FocusRoomDTO> listDTO) {
		if(listDTO == null || listDTO.size() <= 0){
			return ResultUtil.fail(RespCode.Code.REQUEST_DATA_ERROR);
		}
		// 获取当前登录用户
		UserInfoVO su = TokenManager.getLoginUser();
		for(FocusRoomDTO dto : listDTO){
			// 设置默认值
			dto.setCreateTime(new Date());
			dto.setUpdateTime(new Date());
			dto.setCreaterId(su.getId());
			dto.setUpdaterId(su.getId());
			dto.setCompanyId(1L);
		}
		return focusRoomService.batchAddOrUpdate(listDTO);
	}

	/**
	 * 
	 * @Title
	 * @param 房间dto
	 * @Description 修改房间信息
	 * @author 黄伟
	 * @return
	 * @date 2018年5月29日 下午2:03:11
	 */
	@ApiOperation(value = "修改房间信息",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@PostMapping(value = "/update")
	@ResponseBody
	public Result updateRoom(@RequestBody List<FocusRoomDTO> listDTO) {
		if(listDTO == null || listDTO.size() <= 0){
			return ResultUtil.fail(RespCode.Code.REQUEST_DATA_ERROR);
		}
		// 获取当前登录用户
		UserInfoVO su = TokenManager.getLoginUser();
		for(FocusRoomDTO dto : listDTO){
			// 设置默认值
			dto.setUpdateTime(new Date());
			dto.setUpdaterId(su.getId());
		}
		return focusRoomService.batchAddOrUpdate(listDTO);
	}
	
	/**
	 * 
	 * @Title
	 * @param
	 * @Description 删除房间
	 * @author 黄伟
	 * @return 房间配置ID
	 * @date 2018年5月29日 下午2:02:31
	 */
	@ApiOperation(value = "删除房间",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@PostMapping(value = "/deleteRoom/{id}")
	@ResponseBody
	public Result deleteRoom(@PathVariable Long id) {
		return focusRoomService.deleteById(id);
	}
	/**
	 * 
	 * @Title list
	 * @param 房间配置dto
	 * @Description 查询房间配置信息
	 * @author 黄伟
	 * @return 
	 * @date 2018年5月29日 下午2:01:12
	 */
	@ApiOperation(value = "查询房间配置信息",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@GetMapping(value = "/getRoomList")
	@ResponseBody
	public Result getRoomList(FocusRoomDTO dto) {
		// 获取当前用户信息
		UserInfoVO su = TokenManager.getLoginUser();
		dto.setCompanyId(1L);
		return focusRoomService.getRoomList(dto);
	}
	
}
