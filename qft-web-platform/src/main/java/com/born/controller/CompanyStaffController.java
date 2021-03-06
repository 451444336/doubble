package com.born.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.born.config.shiro.token.TokenManager;
import com.born.facade.dto.CompanyPositionDTO;
import com.born.facade.dto.CompanyStaffDTO;
import com.born.facade.dto.ValidateDTO;
import com.born.facade.dto.staff.FindStaffListDTO;
import com.born.facade.service.ICompanyPositionService;
import com.born.facade.service.ICompanyStaffService;
import com.born.facade.service.IPermissionService;
import com.born.facade.vo.CityVO;
import com.born.facade.vo.UserInfoVO;
import com.born.util.result.Result;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @ClassName: CompanyStaffController
 * @Description: 员工Controller
 * @author 明成
 * @date 2018年4月27日 下午4:17:49
 *
 */
@Controller
@RequestMapping(value = "web/staff")
public class CompanyStaffController {

	@Reference(version = "1.0.0")
	private ICompanyStaffService staffService;

	@Reference(version = "1.0.0")
	private IPermissionService permissionService;

	@Reference(version = "1.0.0")
	private ICompanyPositionService companyPositionService;

	/**
	 * 
	 * @Title: index @Description: 跳转员工列表主页 @param @return @author 明成 @return
	 * String @date 2018年5月8日 下午6:20:12 @throws
	 */
	@RequestMapping(value = "/index/{positionId}", method = RequestMethod.GET)
	public String index(@PathVariable(name = "positionId") String positionId, Model model) {
		if ("-1".equals(positionId)) {
			model.addAttribute("positionId", "");
		}
		CompanyPositionDTO positionDTO = new CompanyPositionDTO();
		positionDTO.setCompanyId(TokenManager.getLoginUser().getCompanyId());
		model.addAttribute("select_position", companyPositionService.selectPosition(positionDTO).getData());
		FindStaffListDTO staffListDTO = new FindStaffListDTO();
		staffListDTO.setCompanyId(TokenManager.getLoginUser().getCompanyId());
		model.addAttribute("select_name", staffService.findStaffList(staffListDTO).getData());
		return "user/qft_userList";
	}

	/**
	 * 
	 * @Title: editIndex @Description: 跳转用户编辑与权限修改 @param @return @author 明成 @return
	 * String @date 2018年5月8日 下午6:20:12 @throws
	 */
	@RequestMapping(value = "/editIndex/{userId}/{name}/{positionId}", method = RequestMethod.GET)
	public String editIndex(@PathVariable(name = "userId") String userId, @PathVariable(name = "name") String name,
			@PathVariable(name = "positionId") String positionId, Model model) {
		model.addAttribute("userId", userId);
		model.addAttribute("name", name);
		model.addAttribute("positionId", positionId);
		return "user/qft_permission_user";
	}

	/**
	 * 
	 * @Title: addUser @Description: 添加跳转 @param @return @author 明成 @return
	 * String @date 2018年5月7日 下午7:39:20 @throws
	 */
	@RequestMapping(value = "/addUser/{positionId}", method = RequestMethod.GET)
	public String addUser(@PathVariable String positionId, Model model) {
		model.addAttribute("positionId", positionId);
		CompanyPositionDTO positionDTO = new CompanyPositionDTO();
		positionDTO.setCompanyId(TokenManager.getLoginUser().getCompanyId());
		model.addAttribute("select_position", companyPositionService.selectPosition(positionDTO).getData());
		return "user/qft_add_user";
	}

	/**
	 * 根据员工ID获取员工唯一对象
	 * 
	 * @param dto
	 *            参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "根据员工ID获取员工", notes = "必须传入员工ID")
	@ApiResponses(value = { @ApiResponse(code = 10100, message = "请求参数有误"),
			@ApiResponse(code = 200, message = "操作成功") })
	@RequestMapping(value = "findStaff/{id}")
	public String findStaff(@PathVariable Long id, Model model) {
		Result result = staffService.findStaff(id);
		model.addAttribute("staff", result.getData());
		CompanyPositionDTO positionDTO = new CompanyPositionDTO();
		positionDTO.setCompanyId(TokenManager.getLoginUser().getCompanyId());
		model.addAttribute("select_position", companyPositionService.selectPosition(positionDTO).getData());
		//写死测试
		List<CityVO> cityList = new ArrayList<>();
		CityVO city = new CityVO();
		CityVO city2 = new CityVO();
		CityVO city3 = new CityVO();
		city.setId("北京");
		city.setName("北京");
		cityList.add(city);
		city2.setId("重庆");
		city2.setName("重庆");
		cityList.add(city2);
		city3.setId("四川");
		city3.setName("四川");
		cityList.add(city3);
		model.addAttribute("select_city", cityList);
		return "user/qft_edit_user";
	}

	/**
	 * 根据条件获取员工集合
	 * 
	 * @param dto
	 *            参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "根据条件获取员工集合", notes = "参数填写")
	@ApiResponses(value = { @ApiResponse(code = 10100, message = "请求参数有误"),
			@ApiResponse(code = 200, message = "操作成功") })
	@GetMapping("/findStaffList")
	@ResponseBody
	public Result findStaffList(FindStaffListDTO dto) {
		UserInfoVO user = TokenManager.getLoginUser();
		dto.setCompanyId(user.getCompanyId());
		return staffService.findStaffList(dto);
	}

	/**
	 * 修改员工
	 * 
	 * @param dto
	 *            参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "修改员工", notes = "必须传入员工ID")
	@ApiResponses(value = { @ApiResponse(code = 10100, message = "请求参数有误"),
			@ApiResponse(code = 200, message = "操作成功") })
	@ResponseBody
	@RequestMapping(value = "updateStaff", method = RequestMethod.POST)
	public Result updateStaff(CompanyStaffDTO staff) {
		UserInfoVO user = TokenManager.getLoginUser();
		staff.setUpdaterId(user.getId());
		return staffService.updateStaff(staff);
	}

	/**
	 * 添加员工
	 * 
	 * @param dto
	 *            参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "员工", notes = "确保填写参数是否正确")
	@ApiResponses(value = { @ApiResponse(code = 10100, message = "请求参数有误"),
			@ApiResponse(code = 200, message = "操作成功") })
	@ResponseBody
	@RequestMapping(value = "addStaff", method = RequestMethod.POST)
	public Result addStaff(CompanyStaffDTO dto) {
		UserInfoVO user = TokenManager.getLoginUser();
		dto.setCreaterId(user.getId());
		dto.setCompanyId(user.getCompanyId());
		return staffService.addStaff(dto);
	}

	/**
	 * 逻辑删除员工
	 * 
	 * @param dto
	 *            参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "删除员工", notes = "必须填写员工ID")
	@ApiResponses(value = { @ApiResponse(code = 10100, message = "请求参数有误"),
			@ApiResponse(code = 200, message = "操作成功") })
	@ResponseBody
	@RequestMapping(value = "deleteStaff/{userId}", method = RequestMethod.DELETE)
	public Result deleteStaff(@PathVariable(name = "userId") Long userId) {
		return staffService.deleteStaff(userId);
	}

	/**
	 * 
	 * @Title: getPageList @Description: 分页查询用户列表 @param @param
	 * dto @param @return @author 明成 @return Result @date 2018年5月9日
	 * 上午11:30:16 @throws
	 */
	@ApiOperation(value = "分页查询用户列表", notes = "确保填写参数是否正确")
	@ApiResponses(value = { @ApiResponse(code = 10100, message = "请求参数有误"),
			@ApiResponse(code = 200, message = "操作成功") })
	@GetMapping("/getPageList")
	@ResponseBody
	public Result getPageList(FindStaffListDTO dto) {
		UserInfoVO user = TokenManager.getLoginUser();
		dto.setCompanyId(user.getCompanyId());
		return staffService.getPageList(dto);
	}

	/**
	 * 修改个人权限
	 * 
	 * @param dto
	 *            参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "修改个人权限", notes = "必须传入用户ID")
	@ApiResponses(value = { @ApiResponse(code = 10100, message = "请求参数有误"),
			@ApiResponse(code = 200, message = "操作成功") })
	@ResponseBody
	@RequestMapping(value = "updatePermission", method = RequestMethod.POST)
	public Result updatePermission(FindStaffListDTO dto) {
		return permissionService.addPersonalPermissions(dto.getUserId(), dto.getPermissionIds(),dto.getMeunIds());
	}

	/**
	 * 修改员工状态
	 * 
	 * @param dto
	 *            参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "修改员工状态", notes = "必须传入员工ID")
	@ApiResponses(value = { @ApiResponse(code = 10100, message = "请求参数有误"),
			@ApiResponse(code = 200, message = "操作成功") })
	@ResponseBody
	@RequestMapping(value = "updateUserStu", method = RequestMethod.POST)
	public Result updateUserStu(CompanyStaffDTO staff) {
		UserInfoVO user = TokenManager.getLoginUser();
		staff.setUpdaterId(user.getId());
		return staffService.updateUser(staff);
	}
	
	/**
	 * 验证
	 * 
	 * @param dto
	 *            参数对象
	 * @return Result 返回对象
	 */
	@ApiOperation(value = "验证", notes = "必须传入员工ID")
	@ApiResponses(value = { @ApiResponse(code = 10100, message = "请求参数有误"),
			@ApiResponse(code = 200, message = "操作成功") })
	@ResponseBody
	@RequestMapping(value = "validate", method = RequestMethod.GET)
	public String validate(ValidateDTO dto){
		try {
			return staffService.validation(dto);
		} catch (Exception e) {
			e.printStackTrace();
			return "验证失败";
		}
	}
}
