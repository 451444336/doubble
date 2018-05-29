package com.born.controller.earnest;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.born.config.shiro.token.TokenManager;
import com.born.facade.dto.earnest.EarnestDTO;
import com.born.facade.service.earnest.IEarnestService;
import com.born.facade.vo.UserInfoVO;
import com.born.util.result.Result;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * 
* @ClassName: EarnestController 
* @Description: 定金设置
* @author 明成 
* @date 2018年5月29日 上午9:17:03 
* @version 1.0
 */
@Slf4j
@Controller
@RequestMapping(value = "web/ear")
public class EarnestController {

	@Reference(version = "1.0.0")
	private IEarnestService earnestService;

	/**
	 * 
	* @Title: getPageList 
	* @Description: 获取定金设置列表
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月28日 下午3:12:01 
	* @throws
	 */
	@ApiOperation(value = "获取定金设置列表",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@GetMapping("/getPageList")
	@ResponseBody
	public Result getPageList(EarnestDTO dto){
		UserInfoVO user = TokenManager.getLoginUser();
		dto.setCompanyId(user.getCompanyId());
		return earnestService.getListByPage(dto);
	}
	
	/**
	 * 
	* @Title: getEarnest
	* @Description: 获取定金
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月28日 下午3:12:01 
	* @throws
	 */
	@ApiOperation(value = "获取定金",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@GetMapping("/getEarnest/{id}")
	@ResponseBody
	public Result getEarnest(@PathVariable Long id){
		return earnestService.getById(id);
	}
	
	/**
	 * 
	* @Title: delEarnest
	* @Description: 删除定金
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月28日 下午3:12:01 
	* @throws
	 */
	@ApiOperation(value = "删除定金",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@GetMapping("/delEarnest/{id}")
	@ResponseBody
	public Result delEarnest(@PathVariable Long id){
		return earnestService.delById(id);
	}
	
	/**
	 * 
	* @Title: updateEarnest 
	* @Description: 修改定金
	* @param @param dto
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月28日 下午3:15:29 
	* @throws
	 */
	@ApiOperation(value = "修改定金",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@ResponseBody
	@RequestMapping(value = "updateEarnest",method = RequestMethod.POST)
	public Result updateEarnest(EarnestDTO dto){
		UserInfoVO user = TokenManager.getLoginUser();
		dto.setUpdaterId(user.getId());
		dto.setUpdateTime(new Date());
		return earnestService.updateByModel(dto);
	}
	
	/**
	 * 添加定金
	 * @param dto 参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "添加定金",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@ResponseBody
	@RequestMapping(value = "addEarnest",method = RequestMethod.POST)
	public Result addEarnest(EarnestDTO dto){
		UserInfoVO user = TokenManager.getLoginUser();
		dto.setCreaterId(user.getId());
		dto.setCreateTime(new Date());
		dto.setCompanyId(user.getCompanyId());
		return earnestService.addByModel(dto);
	}
}
