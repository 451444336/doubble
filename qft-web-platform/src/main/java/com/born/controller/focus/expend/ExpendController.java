package com.born.controller.focus.expend;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.born.config.shiro.token.TokenManager;
import com.born.core.page.PageBean;
import com.born.facade.dto.expend.ExpendDTO;
import com.born.facade.service.expend.IExpendService;
import com.born.facade.vo.UserInfoVO;
import com.born.util.result.Result;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
* @ClassName: ExpendController 
* @Description: 应支房租
* @author 明成 
* @date 2018年5月29日 下午3:48:18 
* @version 1.0
 */
@Controller
@RequestMapping(value = "web/exp")
public class ExpendController {

	@Reference(version = "1.0.0")
	private IExpendService expendService;

	/**
	 * 
	* @Title: getPageList 
	* @Description: 获取应支房租列表
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月28日 下午3:12:01 
	* @throws
	 */
	@ApiOperation(value = "获取应支房租列表",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@GetMapping("/getPageList")
	@ResponseBody
	public Result getPageList(ExpendDTO dto,PageBean pageBean){
		UserInfoVO user = TokenManager.getLoginUser();
		dto.setCompanyId(user.getCompanyId());
		return expendService.getListByPage(dto,pageBean);
	}
	/**
	 * 
	* @Title: updateExpend 
	* @Description: 修改应支房租
	* @param @param dto
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月28日 下午3:15:29 
	* @throws
	 */
	@ApiOperation(value = "修改应支房租",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@ResponseBody
	@RequestMapping(value = "updateExpend",method = RequestMethod.POST)
	public Result updateExpend(ExpendDTO dto){
		UserInfoVO user = TokenManager.getLoginUser();
		dto.setCreaterId(user.getId());
		dto.setCreateTime(new Date());
		dto.setCompanyId(user.getCompanyId());
		return expendService.updateByModel(dto);
	}
	
	/**
	 * 添加应支房租
	 * @param dto 参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "添加应支房租",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@ResponseBody
	@RequestMapping(value = "addExpend",method = RequestMethod.POST)
	public Result addExpend(ExpendDTO dto){
		UserInfoVO user = TokenManager.getLoginUser();
		dto.setCreaterId(user.getId());
		dto.setCreateTime(new Date());
		dto.setCompanyId(user.getCompanyId());
		return expendService.addByModel(dto);
	}
	
	/**
	 * 删除应支房租
	 * @param dto 参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "删除应支房租",notes = "必须填写应支ID")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@ResponseBody
	@RequestMapping(value = "delExpend/{id}",method = RequestMethod.GET)
	public Result delExpend(@PathVariable Long id){
		return expendService.delById(id);
	}
	
	/**
	 * 批量添加应支房租
	 * @param dto 参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "批量添加应支房租",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@ResponseBody
	@RequestMapping(value = "BatchAddExpend",method = RequestMethod.POST)
	public Result BatchAddExpend(List<ExpendDTO> dto){
		UserInfoVO user = TokenManager.getLoginUser();
		return expendService.batchAddExpend(dto,user.getCompanyId(),user.getId());
	}
	
	/**
	 * 批量修改应支房租
	 * @param dto 参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "批量修改应支房租",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@ResponseBody
	@RequestMapping(value = "BatchUpdateExpend",method = RequestMethod.POST)
	public Result BatchUpdateExpend(List<ExpendDTO> dto){
		UserInfoVO user = TokenManager.getLoginUser();
		return expendService.batchUpdateExpend(dto,user.getCompanyId(),user.getId());
	}
	
//	/**
//	 * 根据房间主键批量添加应支房租
//	 * @param dto 参数对象
//	 * @return Result 返回对象
//	 */
//	@ApiOperation(value = "根据房间主键批量添加应支房租",notes = "确保填写参数是否正确")
//    @ApiResponses(value = {
//            @ApiResponse(code = 10100,message = "请求参数有误"),
//            @ApiResponse(code = 200,message = "操作成功")
//    })
//	@ResponseBody
//	@GetMapping("/BatchAddExpendByRoomId/{roomId}")
//	public Result BatchAddExpendByRoomId(Long roomId) {
//		UserInfoVO user = TokenManager.getLoginUser();
//		return expendService.batchAddExpendByRoomId(user.getCompanyId(),user.getId());
//	}
	
}
