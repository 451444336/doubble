package com.born.controller.code;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.born.config.shiro.token.TokenManager;
import com.born.facade.dto.code.CodeDTO;
import com.born.facade.service.code.ICodeService;
import com.born.facade.vo.UserInfoVO;
import com.born.util.result.Result;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * 
* @ClassName: CodeController 
* @Description: 编号设置
* @author 明成 
* @date 2018年5月28日 下午2:59:30 
* @version 1.0
 */
@Slf4j
@Controller
@RequestMapping(value = "web/code")
public class CodeController {

	@Reference(version = "1.0.0")
	private ICodeService codeService;

	/**
	 * 
	* @Title: getCodeList 
	* @Description: 获取店面编号设置列表
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月28日 下午3:12:01 
	* @throws
	 */
	@ApiOperation(value = "获取店面编号设置列表",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@GetMapping("/getCodeList")
	@ResponseBody
	public Result getCodeList(CodeDTO dto){
		UserInfoVO user = TokenManager.getLoginUser();
		dto.setCompanyId(user.getCompanyId());
		return codeService.getCodeList(dto);
	}
	/**
	 * 
	* @Title: editDept 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param dto
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月28日 下午3:15:29 
	* @throws
	 */
	@ApiOperation(value = "编辑编号",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@ResponseBody
	@RequestMapping(value = "editCode",method = RequestMethod.POST)
	public Result editCode(CodeDTO dto){
		UserInfoVO user = TokenManager.getLoginUser();
		dto.setCreaterId(user.getId());
		dto.setCreateTime(new Date());
		dto.setCompanyId(user.getCompanyId());
		return codeService.updateCode(dto);
	}
	
	/**
	 * 添加编号
	 * @param dto 参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "添加编号",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@ResponseBody
	@RequestMapping(value = "addCode",method = RequestMethod.POST)
	public Result addCode(CodeDTO dto){
		UserInfoVO user = TokenManager.getLoginUser();
		dto.setCreaterId(user.getId());
		return codeService.addCode(dto);
	}
}
