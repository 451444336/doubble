package com.born.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.born.config.shiro.token.TokenManager;
import com.born.facade.dto.CompanyPositionDTO;
import com.born.facade.service.ICompanyPositionService;
import com.born.facade.vo.UserInfoVO;
import com.born.util.result.Result;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * 
* @ClassName: CompanyPositionController 
* @Description: 职位Controller
* @author 明成
* @date 2018年4月27日 下午4:17:49 
*
 */
@Slf4j
@Controller
@RequestMapping(value = "web/pos")
public class CompanyPositionController{

	@Reference(version = "1.0.0")
	private ICompanyPositionService positionService;
	
	
	
	/**
	 * 
	* @Title: index 
	* @Description: 跳转职位列表主页
	* @param @return
	* @author 明成
	* @return String
	* @date 2018年5月8日 下午6:20:12 
	* @throws
	 */
	 @RequestMapping(value = "/index/{deptId}", method = RequestMethod.GET)
    public String index(@PathVariable String deptId,Model model) {
		 model.addAttribute("deptId",deptId);
        return "user/qft_findplace";
    }
	 
	 /**
		 * 
		* @Title: editIndex 
		* @Description: 跳转职位编辑与权限修改
		* @param @return
		* @author 明成
		* @return String
		* @date 2018年5月8日 下午6:20:12 
		* @throws
		 */
		 @RequestMapping(value = "/editIndex/{positionId}", method = RequestMethod.GET)
	    public String editIndex(@PathVariable String positionId,Model model) {
			model.addAttribute("positionId",positionId);
	        return "user/position_power";
	    }
	 
	/**
	 * 根据职位ID获取职位唯一对象
	 * @param dto 参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "根据职位ID获取职位",notes = "必须传入职位ID")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@ResponseBody
	@RequestMapping(value = "findPosition/{id}",method = RequestMethod.POST)
	public Result findPosition(@PathVariable Long id){
		return positionService.findPosition(id);
	}

	/**
	 * 根据条件获取职位集合
	 * @param dto 参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "根据条件获取职位集合",notes = "参数填写")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@PostMapping("/findPositionList")
	@ResponseBody
	public Result findPositionList(@RequestBody(required=false) CompanyPositionDTO dto){
		return positionService.findPositionList(dto);
	}
	
	/**
	 * 
	* @Title: getPageList 
	* @Description: 分页查询职位列表
	* @param @param dto
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月9日 上午11:30:16 
	* @throws
	 */
	@ApiOperation(value = "分页查询职位列表",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@GetMapping("/getPageList")
	@ResponseBody
	public Result getPageList( CompanyPositionDTO dto) {
		return positionService.getPageList(dto);
	}
	
	/**
	 * 修改职位
	 * @param dto 参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "修改职位",notes = "必须传入职位ID")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@ResponseBody
	@RequestMapping(value = "updatePosition",method = RequestMethod.PUT)
	public Result updatePosition(@RequestBody CompanyPositionDTO dto){
		return positionService.updatePosition(dto);
	}
	
	/**
	 * 添加职位
	 * @param dto 参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "添加职位",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@ResponseBody
	@RequestMapping(value = "addPosition",method = RequestMethod.POST)
	public Result addPosition(@RequestBody CompanyPositionDTO dto){
		return positionService.addPosition(dto);
	}
	
	@ApiOperation(value = "添加职位权限",notes = "添加当前选择的职位下那些权限")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@ResponseBody
	@RequestMapping(value = "add/position/auth/{pId}/{authIds}",method = RequestMethod.POST)
	public Result addPositionAuth(@PathVariable(name="pId") String pId,@PathVariable(name="authIds") String authIds){
		return positionService.addPositionAuth(pId, authIds, String.valueOf(TokenManager.getLoginUser().getId()));
	}
	
	/**
	 * 逻辑删除职位
	 * @param dto 参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "删除职位",notes = "必须填写职位ID")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@ResponseBody
	@RequestMapping(value = "deletePosition/{ids}",method = RequestMethod.GET)
	public Result deletePosition(@PathVariable String ids){
		return positionService.deletePosition(ids);
	}
	
	/**
	 * 
	* @Title: editPosition
	* @Description: 编辑员工
	* @param @param dto
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月9日 下午3:41:51 
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value = "editPosition",method = RequestMethod.POST)
	public Result editPosition(CompanyPositionDTO dto){
		return positionService.editPosition(dto);
	}
}
