package com.born.controller.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.born.config.auth.token.Token;
import com.born.config.shiro.LoginHelper;
import com.born.core.annotation.LimitIPRequest;
import com.born.core.rediscache.ICacheService;
import com.born.dto.LoginInfo;
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
@RequestMapping("api/")
@Api(value = "登录接口", tags = "APP登录接口")
public class LoginApi {

	/**
	 * 缓存服务
	 */
	@Autowired
	private ICacheService<String, Object> iCacheService;
	
	/**
	 *
	 * @Title: login
	 * @Description: 手机端登录接口
	 * @param request
	 * @param loginInfo
	 * @return
	 * @author 张永胜
	 * @return Result
	 * @date 2018年5月4日 下午6:39:04
	 */
	@ApiOperation(value = "登录接口", notes = "APP登录接口")
	@ApiResponses(value = { @ApiResponse(code = 300, message = "操作失败"),
			@ApiResponse(code = 10000, message = "登录失败，处理异常"), @ApiResponse(code = 200, message = "操作成功") })
	@PostMapping("login")
	@LimitIPRequest
	public ResultEntity<Object> login(HttpServletRequest request, @RequestBody(required = false)  LoginInfo loginInfo) {
		log.info("登录接口请求参数 账号={} 密码={}", loginInfo.getAccount(), loginInfo.getPassword());
		return LoginHelper.login(loginInfo, WebUtil.getRemortIP(request));
	}

	/**
	 * 
	 * @Title: logout
	 * @Description: 登出接口
	 * @param request
	 * @param Token
	 * @return
	 * @author 张永胜
	 * @return Result
	 * @date 2018年5月4日 下午6:39:16
	 */
	@ApiOperation(value = "退出接口", notes = "APP登出接口")
	@ApiResponses(value = { @ApiResponse(code = 300, message = "操作失败"), @ApiResponse(code = 200, message = "操作成功") })
	@PostMapping("logout")
	@LimitIPRequest
	public ResultEntity<Object> logout(HttpServletRequest request, @RequestBody(required = false)  Token Token) {
		log.info("接口请求参数 Token={} ", Token.toString());
		return LoginHelper.logout(Token, WebUtil.getRemortIP(request), iCacheService);
	}

}
