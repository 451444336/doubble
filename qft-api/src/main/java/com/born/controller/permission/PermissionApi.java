package com.born.controller.permission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.born.core.annotation.LimitIPRequest;
import com.born.core.annotation.RepeatToken;
import com.born.core.rediscache.ICacheService;
import com.born.facade.dto.permission.AddPermissionDTO;
import com.born.facade.service.ICompanyRoleAuthService;
import com.born.facade.service.IPermissionService;
import com.born.facade.vo.appauth.UserRoleAuthVO;
import com.born.util.constants.AppConstants;
import com.born.util.json.JsonResult;
import com.born.util.json.ResultCode;
import com.born.util.json.ResultEntity;
import com.born.util.result.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/permissions")
@Api(value = "权限接口", tags = "APP权限接口")
public class PermissionApi {

	/**
	 * 权限服务
	 */
	@Reference(version = "1.0.0")
	private IPermissionService permissionService;
	
	/**
	 * 缓存服务
	 */
	@Autowired
	private ICacheService<String, Object> iCacheService;

	/**
	 * 角色权限服务
	 */
	@Reference(version = "1.0.0")
	private ICompanyRoleAuthService iCompanyRoleAuthService;
	
	/**
	 * 
	 * @Title: queryPermission
	 * @Description: 查询到的数据有角色、菜单、权限，层级关系结构
	 * @param userId
	 *            用户ID
	 * @return
	 * @author 张永胜
	 * @return Result
	 * @date 2018年5月4日 下午6:40:22
	 */
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "获取权限数据", notes = "APP登录成功获取才能获取(角色菜单权限数据等)")
	@ApiResponses(value = { @ApiResponse(code = 10100, message = "请求参数有误"),
			@ApiResponse(code = 200, message = "操作成功") })
	@GetMapping("/{userId}")
	@LimitIPRequest
	public ResultEntity<Object> queryPermission(@PathVariable String userId) {
		log.info("接口请求参数 userId={} ", userId);
		Assert.notNull(userId, "userId");

		/**
		 * 先从缓存中获取权限
		 */
		Object permission = iCacheService.get(AppConstants.USER_PERMISSION_INFO + ":" + userId);
		if (permission != null) {
			List<UserRoleAuthVO> roleAuthList = (List<UserRoleAuthVO>) permission;
			return JsonResult.info(ResultCode.SUCCESS, roleAuthList);
		}

		/**
		 * 缓存没有就从数据库中获取权限信息
		 */
		Result result = iCompanyRoleAuthService.findRoleAuthList(userId);
		return JsonResult.info(ResultCode.SUCCESS, result.getData());
	}
	
	/**
	 * 
	* @Title: addPermission 
	* @Description: 权限添加
	* @param @param param
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	@ApiOperation(value = "权限添加",notes = "[李杰]权限添加")
    @ApiResponses(value = {
            @ApiResponse(code = 10100,message = "请求参数有误"),
            @ApiResponse(code = 200,message = "操作成功"),
            @ApiResponse(code = 300,message = "操作失败"),
            @ApiResponse(code = 500,message = "服务异常")
    })
	@PostMapping("anon")
	@RepeatToken(key = "companyId")
	public Result addPermission(@RequestBody AddPermissionDTO dto) {
		log.info("权限添加请求参数={}", JSON.toJSONString(dto));
		Result result = permissionService.addPermission(dto);
		log.info("添加权限返回数据={}", JSON.toJSONString(result));
		return result;
	}
}
