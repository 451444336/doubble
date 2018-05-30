package com.born.controller.focus.income;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.born.config.shiro.token.TokenManager;
import com.born.core.page.PageBean;
import com.born.facade.dto.income.IncomeDTO;
import com.born.facade.service.income.IIncomeService;
import com.born.facade.vo.UserInfoVO;
import com.born.util.result.Result;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
* @ClassName: IncomeController 
* @Description: 应收房租
* @author 明成 
* @date 2018年5月29日 下午3:48:18 
* @version 1.0
 */
@Controller
@RequestMapping(value = "web/income")
public class IncomeController {

	@Reference(version = "1.0.0")
	private IIncomeService incomeService;

	/**
	 * 
	* @Title: getPageList 
	* @Description: 获取应收房租列表
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月28日 下午3:12:01 
	* @throws
	 */
	@ApiOperation(value = "获取应收房租列表",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@GetMapping("/getPageList")
	@ResponseBody
	public Result getPageList(IncomeDTO dto,PageBean pageBean){
		UserInfoVO user = TokenManager.getLoginUser();
		dto.setCompanyId(user.getCompanyId());
		return incomeService.getListByPage(dto,pageBean);
	}
	/**
	 * 
	* @Title: updateIncome 
	* @Description: 修改应收房租
	* @param @param dto
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月28日 下午3:15:29 
	* @throws
	 */
	@ApiOperation(value = "修改应收房租",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@ResponseBody
	@RequestMapping(value = "updateIncome",method = RequestMethod.POST)
	public Result updateIncome(IncomeDTO dto){
		UserInfoVO user = TokenManager.getLoginUser();
		dto.setCreaterId(user.getId());
		dto.setCreateTime(new Date());
		dto.setCompanyId(user.getCompanyId());
		return incomeService.updateByModel(dto);
	}
	
	/**
	 * 添加应收房租
	 * @param dto 参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "添加应收房租",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@ResponseBody
	@RequestMapping(value = "addIncome",method = RequestMethod.POST)
	public Result addIncome(IncomeDTO dto){
		UserInfoVO user = TokenManager.getLoginUser();
		dto.setCreaterId(user.getId());
		dto.setCreateTime(new Date());
		dto.setCompanyId(user.getCompanyId());
		return incomeService.addByModel(dto);
	}
	
	/**
	 * 删除应收房租
	 * @param dto 参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "删除应收房租",notes = "必须填写应收ID")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@ResponseBody
	@RequestMapping(value = "delIncome/{id}",method = RequestMethod.GET)
	public Result delIncome(@PathVariable Long id){
		return incomeService.delById(id);
	}
	/**
	 * 合并应收房租
	 * @param dto 参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "合并应收房租",notes = "请求参数有误")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@ResponseBody
	@RequestMapping(value = "mergeIncome/{id}/{oneId}",method = RequestMethod.GET)
	public Result mergeIncome(@PathVariable(name="id") Long id,@PathVariable(name="oneId") Long oneId){
		return incomeService.mergeIncome(id,oneId);
	}
}
