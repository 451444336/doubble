package com.born.controller.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.born.config.shiro.LoginHelper;
import com.born.core.annotation.LimitIPRequest;
import com.born.dto.RegisterInfo;
import com.born.facade.service.ISysUserService;
import com.born.util.json.ResultEntity;
import com.born.util.web.WebUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/register")
@Api(value = "注册接口", tags = "APP注册接口")
public class RegisterApi {

	/**
	 * 用户服务
	 */
	@Reference(version = "1.0.0")
	private ISysUserService iSysUserService;

	/**
	 * 
	 * @Title: register
	 * @Description: APP 注册账户接口
	 * @param request
	 * @param registerInfo
	 * @return
	 * @author 张永胜
	 * @return Result
	 * @date 2018年5月4日 下午6:39:40
	 */
	@ApiOperation(value = "注册接口", notes = "APP注册接口")
	@ApiResponses(value = { @ApiResponse(code = 300, message = "操作失败"), @ApiResponse(code = 200, message = "操作成功") })
	@PostMapping
	@LimitIPRequest
	public ResultEntity<Object> register(HttpServletRequest request,
			@RequestBody(required = false) RegisterInfo registerInfo) {
		log.info("注册接口请求参数 账号={} 密码={}", registerInfo.getAccount(), registerInfo.getPassword());
		return LoginHelper.register(registerInfo, WebUtil.getRemortIP(request), iSysUserService);
	}

}
