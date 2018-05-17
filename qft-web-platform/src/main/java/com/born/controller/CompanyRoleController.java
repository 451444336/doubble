/**
 * 
 */
package com.born.controller;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.born.config.shiro.token.TokenManager;
import com.born.facade.dto.CompanyRoleDTO;
import com.born.facade.dto.OperateLogAuthorityDTO;
import com.born.facade.service.ICompanyRoleService;
import com.born.facade.vo.UserInfoVO;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 角色管理控制类
 * @author wangxy
 * @date 2018年5月2日 上午10:06:42
 */
@Slf4j
@Controller
@RequestMapping(value = "/web/role")
public class CompanyRoleController {

	@Reference(version = "1.0.0")
	ICompanyRoleService companyRoleService;

	/**
	 * @Description 保存角色
	 * @author wangxy
	 * @date 2018年5月2日 上午10:35:03
	 * @param role
	 * @return
	 */
	@ApiOperation(value = "添加角色",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@PostMapping(value = "/save")
	@ResponseBody
	public Result saveRole(String roleName , int isAuthEdit) {
		// 验证参数
		if (StringUtils.isBlank(roleName) || (isAuthEdit != 0 && isAuthEdit != 1)) {
			return ResultUtil.getResult(RespCode.Code.REQUEST_DATA_ERROR);
		}
		CompanyRoleDTO role = new CompanyRoleDTO();
		role.setRoleName(roleName);
		role.setIsAuthEdit(isAuthEdit);
		
		// 获取当前登录用户
		UserInfoVO su = TokenManager.getLoginUser();
		role.setCreaterId(su.getId());
		role.setCompanyId(su.getCompanyId());
		return companyRoleService.insert(role);
	}

	/**
	 * @Description 修改角色
	 * @author wangxy
	 * @date 2018年5月2日 上午10:57:35
	 * @param role
	 * @return
	 */
	@ApiOperation(value = "修改角色",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@PostMapping(value = "/update")
	@ResponseBody
	public Result updateRole(String roleName, Long id, int isAuthEdit) {
		// 验证参数
		if (StringUtils.isBlank(roleName) || id == null || id < 0 || (isAuthEdit != 0 && isAuthEdit != 1)) {
			return ResultUtil.getResult(RespCode.Code.REQUEST_DATA_ERROR);
		}
		// 获取当前用户信息
		UserInfoVO su = TokenManager.getLoginUser();
		
		CompanyRoleDTO role = new CompanyRoleDTO();
		role.setId(id);
		role.setRoleName(roleName);
		role.setIsAuthEdit(isAuthEdit);
		// 设置默认值
		role.setUpdateTime(new Date());
		role.setUpdaterId(su.getId());
		return companyRoleService.update(role);
	}

	/**
	 * @Description 删除角色
	 * @author wangxy
	 * @date 2018年5月2日 上午11:06:45
	 * @param id
	 *            角色ID
	 * @return
	 */
	@ApiOperation(value = "删除角色",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@PostMapping(value = "/delete/{id}")
	@ResponseBody
	public Result deleteRole(@PathVariable Long id) {
		UserInfoVO su = TokenManager.getLoginUser();
		OperateLogAuthorityDTO dto = new OperateLogAuthorityDTO();
		dto.setCreaterId(su.getId());
		dto.setCreateTime(new Date());
		dto.setCompanyId(su.getCompanyId());
		dto.setTypeId(id);
		dto.setType((byte)1);
		dto.setOperate("删除角色");
		return companyRoleService.deleteById(id,dto);
	}

	/**
	 * @Description 绑定菜单
	 * @author wangxy
	 * @date 2018年5月2日 上午11:36:21
	 * @param menuIds
	 *            菜单ids
	 * @param roleId
	 *            角色ID
	 * @return
	 */
	@ApiOperation(value = "绑定菜单",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@PostMapping(value = "/bindMenus")
	@ResponseBody
	public Result bindMenus(String menuIds, Long roleId) {
		// 验证参数
		if (StringUtils.isBlank(menuIds) || roleId == null) {
			return ResultUtil.getResult(RespCode.Code.REQUEST_DATA_ERROR);
		}
		// 拆分字符串 1,2,3,4,5,
		String[] split = menuIds.split(",");
		Long[] ids = new Long[split.length];
		for (int i = 0; i < split.length; i++) {
			ids[i] = Long.parseLong(split[i]);
		}
		// 获取当前用户信息
		UserInfoVO su = TokenManager.getLoginUser();
		return companyRoleService.bindRoleMenu(ids, roleId, su.getId(), new Date());
	}
	
	/**
	 * 
	* @Title: list 
	* @Description: 跳转
	* @param @return
	* @author 黄伟
	* @return String
	* @date 2018年5月9日 下午1:36:28 
	* @throws
	 */
	@RequestMapping(value = "/roleList", method = RequestMethod.GET)
    public String roleList() {
        return "qft_roleList";
    }
	/**
	 * @Description 分页查询角色列表
	 * @author 黄伟
	 * @date 2018年5月9日 上午11:36:21
	 */
	@ApiOperation(value = "分页查询角色列表",notes = "确保填写参数是否正确")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@GetMapping(value = "/getPageList")
	@ResponseBody
	public Result getPageList(CompanyRoleDTO dto) {
		return companyRoleService.getPageList(dto);
	}
	
	/**
	 * @Description 根据角色ID查询角色菜单
	 * @author 黄伟
	 * @date 2018年5月9日 下午3:36:28
	 * @param roleId
	 * @return
	 */
	@ApiOperation(value = "根据角色ID查询角色菜单",notes = "必须填写角色ID")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功")
    })
	@PostMapping(value = "/getRoleMenus")
	@ResponseBody
	public Result getRoleMenus(Long roleId){
		
		return companyRoleService.getRoleMenus(roleId);
	}
}
