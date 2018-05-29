/**
 * 
 */
package com.born.controller.focus.housing;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.born.config.shiro.token.TokenManager;
import com.born.facade.dto.focus.housing.FocusHousingDTO;
import com.born.facade.service.focus.housing.IFocusHousingService;
import com.born.facade.vo.UserInfoVO;
import com.born.facade.vo.focus.housing.FocusHousingVO;
import com.born.util.result.Result;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @description 集中整租房源控制类
 * @author 黄伟
 * @date 2018年5月29日 下午1:43:39
 */
@Controller
@RequestMapping(value = "/web/housing")
public class FocusHousingController {

	@Reference(version = "1.0.0")
	IFocusHousingService focusHousingService;

	/**
	 * 
	 * @Title
	 * @param 房源dto
	 * @Description 添加房源信息
	 * @author 黄伟
	 * @return
	 * @date 2018年5月29日 下午2:04:04
	 */
	@ApiOperation(value = "添加房源信息",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@PostMapping(value = "/add")
	@ResponseBody
	public Result addHousing(FocusHousingDTO dto) {
		// 获取当前登录用户
		UserInfoVO su = TokenManager.getLoginUser();
		// 设置默认值
		dto.setCreateTime(new Date());
		dto.setUpdateTime(new Date());
		dto.setCreaterId(su.getId());
		dto.setUpdaterId(su.getId());
		dto.setCompanyId(1L);
		return focusHousingService.addOrUpdate(dto);
	}

	/**
	 * 
	 * @Title
	 * @param 房源ID
	 * @Description 根据房源信息ID查询房源信息
	 * @author 黄伟
	 * @return
	 * @date 2018年5月29日 下午2:03:39
	 */
	@ApiOperation(value = "根据房源信息ID查询房源信息",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@GetMapping(value = "/getHousingById/{id}")
	public String getHousingById(@PathVariable Long id, Model model){
		
		Result result = focusHousingService.getHousingById(id);
		FocusHousingVO vo = new FocusHousingVO();
		BeanUtils.copyProperties(result.getData(), vo);
		// 获取当前登录用户
		UserInfoVO su = TokenManager.getLoginUser();
		
		
		model.addAttribute("housing", vo);
		return "housing/qft_update";
	}
	
	/**
	 * 
	 * @Title
	 * @param 房源dto
	 * @Description 修改房源信息
	 * @author 黄伟
	 * @return
	 * @date 2018年5月29日 下午2:03:11
	 */
	@ApiOperation(value = "修改房源信息",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@PostMapping(value = "/update")
	@ResponseBody
	public Result updateHousing(FocusHousingDTO dto) {
		// 获取当前用户信息
		UserInfoVO su = TokenManager.getLoginUser();
		// 设置默认值
		dto.setUpdateTime(new Date());
		dto.setUpdaterId(su.getId());
		return focusHousingService.addOrUpdate(dto);
	}

	/**
	 * 
	 * @Title
	 * @param
	 * @Description 删除房源信息
	 * @author 黄伟
	 * @return 房源ID
	 * @date 2018年5月29日 下午2:02:31
	 */
	@ApiOperation(value = "删除房源信息",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@PostMapping(value = "/delete/{id}")
	@ResponseBody
	public Result deleteHousing(@PathVariable Long id) {
		return focusHousingService.deleteById(id);
	}
	
	/**
	 * 
	 * @Title
	 * @param
	 * @Description 跳转到查询列表
	 * @author 黄伟
	 * @return String
	 * @date 2018年5月29日 下午2:02:04
	 */
	@GetMapping(value = "/housingList")
    public String storeList() {
        return "housing/qft_housingList";
    }

	/**
	 * 
	 * @Title list
	 * @param 房源dto
	 * @Description 分页查询房源信息列表
	 * @author 黄伟
	 * @return 
	 * @date 2018年5月29日 下午2:01:12
	 */
	@ApiOperation(value = "分页查询房源信息列表",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@GetMapping(value = "/getHousingList")
	@ResponseBody
	public Result getHousingList(FocusHousingDTO dto) {
		// 获取当前用户信息
		UserInfoVO su = TokenManager.getLoginUser();
		dto.setCompanyId(1L);
		return focusHousingService.getHousingList(dto);
	}
	
	/**
	 * 
	 * @Title
	 * @param 托管信息ID
	 * @Description 根据托管信息ID查询不规则递增
	 * @author 黄伟
	 * @return
	 * @date 2018年5月29日 下午2:07:28
	 */
	@ApiOperation(value = "根据托管信息ID查询不规则递增",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@PostMapping(value = "/getAddIrregularAll")
	@ResponseBody
	public Result getAddIrregularAll(Long trustId){
		
		return focusHousingService.getAddIrregularAll(trustId);
	}
}
