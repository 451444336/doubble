package com.born.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.born.facade.dto.user.UserPassDTO;
import com.born.facade.service.IUserService;
import com.born.util.result.Result;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @ClassName: UserController
 * @Description: web层操作用户信息
 * @author 张永胜
 * @date 2018年5月9日 上午11:17:03
 * @version 1.0
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

	/**
	 * 用户服务
	 */
	@Reference(version = "1.0.0")
	private IUserService userService;

	@ApiOperation(value = "修改用户密码", notes = "修改用户密码")
	@ApiResponses(value = { @ApiResponse(code = 10100, message = "请求参数有误"),
			@ApiResponse(code = 200, message = "操作成功") })
	@ResponseBody
	@RequestMapping(value = "/update/pass", method = RequestMethod.POST)
	public Result updatePassWord(@ModelAttribute(value = "user") UserPassDTO user) {

		/*
		 * 此时密码没有加密(过后需要优化此处)
		 */
		//UserInfoVO userInfoVO = TokenManager.getLoginUser();
		//String operatingId = String.valueOf(userInfoVO.getId());
		String operatingId = "1";
		return userService.updateUserPass(user.getId(), user.getPassword(), user.getAccount(), operatingId);
	}
}
