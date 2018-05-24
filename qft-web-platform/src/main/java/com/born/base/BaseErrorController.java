package com.born.base;


import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.born.core.exception.BizException;
import com.born.facade.exception.PermissionExceptionEnum;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;

import lombok.extern.slf4j.Slf4j;


/**
 * 
* @ClassName: BaseErrorController 
* @Description: 异常处理 
* @author lijie 
* @date 2018年5月9日 上午11:26:54 
*
 */
@ControllerAdvice
@Slf4j
public class BaseErrorController {


    /**
     * 500异常处理
     *
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
		log.error("web 500异常信息", e);
		ModelAndView view = new ModelAndView();
		view.addObject("code", 500);
		view.addObject("msg", e.getMessage());
		view.addObject("url", request.getRequestURL());
		view.setViewName("error/500");
		return view;
	}

    /**
     * 404异常处理
     *
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
	@ExceptionHandler(value = NoHandlerFoundException.class)
	public ModelAndView pageNoFoundHandler(HttpServletRequest request, Exception e) {
		log.error("web 404异常信息", e);
		ModelAndView view = new ModelAndView();
		view.addObject("code", 404);
		view.addObject("msg", "页面未找到");
		view.addObject("url", request.getRequestURL());
		view.setViewName("error/404");
		return view;
	}

    /**
     * biz业务异常处理
     *
     * @param e
     * @return
     * @throws Exception
     */
	@ExceptionHandler(value = BizException.class)
	@ResponseBody
	public Result bizExceptionHandler(BizException e) {
		log.error("异常信息, msg={},code={},e", e.getMsg(), e.getCode(), e);
		return new Result(e.getCode(), e.getMsg());
	}
	/**
	 * 
	* @Title: unknown  
	* @Description: 未知用户错误 
	* @param: @param e
	* @param: @return
	* @return ModelAndView
	* @author lijie
	* @throws
	 */
	@ExceptionHandler(value = UnknownAccountException.class)
	public @ResponseBody Result unknown(UnknownAccountException e) {
		log.info("对用户进行登录验证..验证未通过,未知账户", e);
		return ResultUtil.getResult(PermissionExceptionEnum.UNKNOWN_ACCOUNT);
	}
	/**
	 * 
	* @Title: incorrectCredentialsException  
	* @Description: 错误的凭证 
	* @param: @param e
	* @param: @return
	* @return ModelAndView
	* @author lijie
	* @throws
	 */
	@ExceptionHandler(value = IncorrectCredentialsException.class)
	public @ResponseBody Result incorrectCredentialsException(IncorrectCredentialsException e) {
		log.info("对用户进行登录验证..验证未通过,错误的凭证", e);
		return ResultUtil.getResult(PermissionExceptionEnum.INCORRECT_CREDENTIALS);
	}
	/**
	 * 
	* @Title: lockedAccountException  
	* @Description: 账户已锁定 
	* @param: @param e
	* @param: @return
	* @return ModelAndView
	* @author lijie
	* @throws
	 */
	@ExceptionHandler(value = LockedAccountException.class)
	public @ResponseBody Result lockedAccountException(LockedAccountException e) {
		log.info("对用户进行登录验证..验证未通过,账户已锁定", e);
		return ResultUtil.getResult(PermissionExceptionEnum.LOCKED_ACCOUNT);
	}
	/**
	 * 
	* @Title: excessiveAttemptsException  
	* @Description: 错误次数过多 
	* @param: @param e
	* @param: @return
	* @return ModelAndView
	* @author lijie
	* @throws
	 */
	@ExceptionHandler(value = ExcessiveAttemptsException.class)
	public @ResponseBody Result excessiveAttemptsException(ExcessiveAttemptsException e) {
		log.info("对用户进行登录验证..验证未通过,错误次数过多", e);
		return ResultUtil.getResult(PermissionExceptionEnum.EXCESSIVE_ATTEMPTS);
	}
	/**
	 * 
	* @Title: authenticationException  
	* @Description: TODO 
	* @param: @param e
	* @param: @return
	* @return ModelAndView
	* @author lijie
	* @throws
	 */
	@ExceptionHandler(value = AuthenticationException.class)
	public @ResponseBody Result authenticationException(AuthenticationException e) {
		log.info("对用户进行登录验证..验证未通过,堆栈轨迹如下", e);
		return ResultUtil.getResult(PermissionExceptionEnum.AUTHENTICATION);
	}
	/**
	 * 
	* @Title: accountException 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param e
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @author lijie
	* @throws
	 */
	@ExceptionHandler(value = AccountException.class)
	public @ResponseBody Result accountException(AccountException e) {
		log.info("对用户进行登录验证..验证未通过,账号密码错误", e);
		return ResultUtil.getResult(PermissionExceptionEnum.AUTHENTICATION);
	}
	/**
	 * 
	* @Title: disabledAccountException 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param e
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @author lijie
	* @throws
	 */
	@ExceptionHandler(value = DisabledAccountException.class)
	public @ResponseBody Result disabledAccountException(DisabledAccountException e) {
		log.info("对用户进行登录验证..验证未通过,帐号已经禁止登录", e);
		return ResultUtil.getResult(PermissionExceptionEnum.USER_LOGOUT);
	}
}
