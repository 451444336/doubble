package com.born.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.born.config.shiro.token.TokenManager;
import com.born.facade.dto.dept.CompanyDeptDTO;
import com.born.facade.service.ICompanyDeptService;
import com.born.facade.vo.UserInfoVO;
import com.born.util.result.Result;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * 
* @ClassName: CompanyDeptController 
* @Description: 部门Controller
* @author 明成
* @date 2018年4月27日 下午4:17:49 
*
 */
@Slf4j
@Controller
@RequestMapping(value = "web/dept")
public class CompanyDeptController {

	@Reference(version = "1.0.0")
	private ICompanyDeptService deptService;
	
	/**
	 * 
	* @Title: index 
	* @Description: 根据公司跳转部门列表主页
	* @param @return
	* @author 明成
	* @return String
	* @date 2018年5月8日 下午6:20:12 
	* @throws
	 */
	 @RequestMapping(value = "/index/{companyId}", method = RequestMethod.GET)
    public String index(@PathVariable String companyId,Model model) {
		 model.addAttribute("companyId",companyId);
        return "user/qft_findbranch";
    }
	
	/**
	 * 
	* @Title: list 
	* @Description: 跳转
	* @param @return
	* @author 明成
	* @return String
	* @date 2018年5月7日 下午4:42:18 
	* @throws
	 */
   @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "user/qft_userSetting";
    }
   
   /**
    * 
   * @Title: userList 
   * @Description: 跳转
   * @param @return
   * @author 明成
   * @return String
   * @date 2018年5月7日 下午4:42:34 
   * @throws
    */
   @RequestMapping(value = "/userList", method = RequestMethod.GET)
   public String userList() {
       return "user/qft_userList";
   }
	/**
	 * 根据部门ID获取部门唯一对象
	 * @param dto 参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "根据部门ID获取部门",notes = "必须传入部门ID")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@ResponseBody
	@RequestMapping(value = "findDept/{id}",method = RequestMethod.POST)
	public Result findDept(@PathVariable Long id){
		return deptService.findDept(id);
	}

	/**
	 * 
	* @Title: findOrgList 
	* @Description: 根据公司ID获取组织架构
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月8日 下午5:33:40 
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value = "findOrgList",method = RequestMethod.POST)
	public Result findOrgList(){
		//先写死公司ID
		UserInfoVO user = TokenManager.getLoginUser();
		return deptService.findOrgList(user.getCompanyId());
	}
	
//	/**
//	 * 根据条件获取部门集合
//	 * @param dto 参数对象
//	 * @return Result 返回对象
//	 */
//	@ApiOperation(value = "根据条件获取部门集合",notes = "参数填写")
//    @ApiResponses(value = {
//            @ApiResponse(code = 10100,message = "请求参数有误"),
//            @ApiResponse(code = 200,message = "操作成功")
//    })
//	@GetMapping("/findDeptList")
//	@ResponseBody
//	public Result findDeptList(CompanyDeptDTO dto){
//		return deptService.findDeptList(dto);
//	}
	
	/**
	 * 
	* @Title: getPageList 
	* @Description: 分页查询部门列表"
	* @param @param dto
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月9日 上午11:30:51 
	* @throws
	 */
	@ApiOperation(value = "分页查询部门列表",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@GetMapping("/getPageList")
	@ResponseBody
	public Result getPageList( CompanyDeptDTO dto) {
		return deptService.getPageList(dto);
	}
	
//	/**
//	 * 修改部门
//	 * @param dto 参数对象
//	 * @return Result 返回对象
//	 */
//	@ApiOperation(value = "修改部门",notes = "必须传入部门ID")
//    @ApiResponses(value = {
//            @ApiResponse(code = 10100,message = "请求参数有误"),
//            @ApiResponse(code = 200,message = "操作成功")
//    })
//	@ResponseBody
//	@RequestMapping(value = "updateDept",method = RequestMethod.PUT)
//	public Result updateDept(@RequestBody CompanyDeptDTO dto){
//		return deptService.updateDept(dto);
//	}
	
//	/**
//	 * 添加部门
//	 * @param dto 参数对象
//	 * @return Result 返回对象
//	 */
//	@ApiOperation(value = "添加部门",notes = "确保填写参数是否正确")
//    @ApiResponses(value = {
//            @ApiResponse(code = 10100,message = "请求参数有误"),
//            @ApiResponse(code = 200,message = "操作成功")
//    })
//	@ResponseBody
//	@RequestMapping(value = "addDept",method = RequestMethod.POST)
//	public Result addDept(@RequestBody CompanyDeptDTO dto){
//		return deptService.addDept(dto);
//	}
	
	/**
	 * 逻辑删除部门
	 * @param dto 参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "删除部门",notes = "必须填写部门ID")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@ResponseBody
	@RequestMapping(value = "deleteDept/{ids}",method = RequestMethod.GET)
	public Result deleteDept(@PathVariable String ids){
		CompanyDeptDTO dto = new CompanyDeptDTO();
		dto.setIds(ids);
		UserInfoVO user = TokenManager.getLoginUser();
		dto.setUserId(user.getId());
		return deptService.deleteDept(dto);
	}
	
	/**
	 * 
	* @Title: editDept 
	* @Description: 编辑部门
	* @param @param dto
	* @param @return
	* @author 明成
	* @return Result
	* @date 2018年5月9日 下午3:41:51 
	* @throws
	 */
	@ApiOperation(value = "编辑部门",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@ResponseBody
	@RequestMapping(value = "editDept",method = RequestMethod.POST)
	public Result editDept(CompanyDeptDTO dto){
		//获取web登录人信息
		UserInfoVO user = TokenManager.getLoginUser();
		dto.setUserId(user.getId());
		dto.setCompanyId(user.getCompanyId());
		return deptService.editDept(dto);
	}
}
