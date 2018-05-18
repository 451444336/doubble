package com.born.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.born.config.shiro.MyShiroRealm;
import com.born.config.shiro.token.ShiroToken;
import com.born.config.shiro.token.TokenManager;
import com.born.facade.dto.user.UserDTO;
import com.born.facade.exception.PermissionExceptionEnum;
import com.born.facade.service.IPermissionService;
import com.born.facade.service.IUserService;
import com.born.facade.vo.UserInfoVO;
import com.born.facade.vo.UserRoleVO;
import com.born.facade.vo.company.CompanyInfoVO;
import com.born.util.encrypt.password.PasswordUtil;
import com.born.util.http.IPUtil;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;
import com.born.util.session.SessionUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
* @ClassName: LoginController 
* @Description: 登录 
* @author lijie 
* @date 2018年5月2日 下午4:38:45 
*
 */
@Slf4j
@Controller
public class LoginController {


    @Autowired
    private MyShiroRealm myShiroRealm;
    
    @Reference(version="1.0.0")
    private IUserService userService;
    
    @Reference(version="1.0.0")
    private IPermissionService permissionService;
    /**
     * 
    * @Title: loginPage 
    * @Description: 登录跳转
    * @param @return    设定文件 
    * @return String    返回类型 
    * @author lijie
    * @throws
     */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loginPage(HttpServletRequest request) {
		// TODO:没有输入公司则跳转首页
		return "login";
	}
	
   /**
    * 
   * @Title: index 
   * @Description: 跳转首页
   * @param @return    设定文件 
   * @return ModelAndView    返回类型 
   * @author lijie
   * @throws
    */
	@RequestMapping(value = "/web/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView result = new ModelAndView("index");
		// 返回index需要的用户名和角色信息
		UserInfoVO su = TokenManager.getLoginUser();
		if (null != su) {
			result.addObject("userId", su.getId());
			result.addObject("userName", su.getUserName());
			result.addObject("corUrl", su.getCorUrl());
			StringBuilder sb = new StringBuilder();
			if (CollectionUtils.isNotEmpty(su.getRoles())) {
				for (UserRoleVO sr : su.getRoles()) {
					sb.append(sr.getRoleName()).append(",");
				}
				result.addObject("roleName", sb.deleteCharAt(sb.length() - 1));
			} else {
				result.setViewName("login");
				result.addObject("code", PermissionExceptionEnum.NO_ROLE.getCode());
				result.addObject("msg", PermissionExceptionEnum.NO_ROLE.getMsg());
			}
		}
		return result;
	}


    /**
     * 
    * @Title: login 
    * @Description: 跳转登录
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @author lijie
    * @throws
     */
	@RequestMapping(value = "/{corUrl}", method = RequestMethod.GET)
	public String loginByCorUrl(@PathVariable(required = false) String corUrl, Model model,
			HttpServletRequest request) {
		if ("login".equals(corUrl)) {
			corUrl = SessionUtil.getCorUrl(request);
			if (StringUtils.isNotBlank(corUrl) && !"login".equals(corUrl)) {
				return "redirect:/" + corUrl;
			} else {
				return "redirect:/";
			}
		}
		if (StringUtils.isNotBlank(corUrl)) {
			Result cor = permissionService.getCompanyInfoByCorUrl(corUrl);
			if (cor.isSuccess()) {
				model.addAttribute("corUrl", corUrl);
				SessionUtil.setCorUrl(request, corUrl);
				return "login";
			} else {
				model.addAttribute("message", "公司不存在");
				return "login";
			}
		}
		return "redirect:404";
	}
	/**
	 * 
	* @Title: jum404 
	* @Description: 跳转404
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author lijie
	* @throws
	 */
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String jum404(){
		
		return "error/404";
	}
    /**
     * 
    * @Title: logout 
    * @Description:  注销登录
    * @param @return    设定文件 
    * @return String    返回类型 
    * @author lijie
    * @throws
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
    	String corUrl = "";
        try {
        	corUrl = SessionUtil.getCorUrl(request);
            SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            log.error("注销登录处理错误", e);
        }
        return "redirect:/" + corUrl;
    }


    /**
     * 
    * @Title: unauthorizedRole 
    * @Description: 重定向到没有权限返回页面
    * @param @return    设定文件 
    * @return String    返回类型 
    * @author lijie
    * @throws
     */
    @RequestMapping("/403")
    public String unauthorizedRole() {
        return "error/403";
    }


    /**
     * 
    * @Title: noPermission 
    * @Description: 没有权限返回页面
    * @param @return    设定文件 
    * @return String    返回类型 
    * @author lijie
    * @throws
     */
    @RequestMapping("/noPermission")
    public String noPermission() {
        return "error/403";
    }



   /**
    * 
   * @Title: login 
   * @Description: 交给shiro管理登录
   * @param @param user
   * @param @param rememberMe
   * @param @param msgCode
   * @param @param request
   * @param @return    设定文件 
   * @return Object    返回类型 
   * @author lijie
   * @throws
    */
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public @ResponseBody Result login(UserDTO user, boolean rememberMe, HttpServletRequest request) {
		log.info("登录请求参数入参={}", JSON.toJSONString(user));
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		if (StringUtils.isBlank(user.getCorUrl())) {
			result.setMessage("公司不存在");
			return result;
		}
		Result cor = permissionService.getCompanyInfoByCorUrl(user.getCorUrl());
		if (!cor.isSuccess()) {
			result.setMessage("公司不存在");
			return result;
		}
		// MD5加密
		user.setPassword(PasswordUtil.encrypt(user.getAccount(), user.getPassword()));
		ShiroToken token = new ShiroToken(user.getAccount(), user.getPassword(), rememberMe,
				IPUtil.getIpAddress(request));
		// 获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();
		log.info("对用户[" + user.getAccount() + "]进行登录验证..验证开始");
		currentUser.login(token);
		log.info("对用户[" + user.getAccount() + "]进行登录验证..验证通过");
		// 验证是否登录成功
		if (currentUser.isAuthenticated()) {
			log.info("用户[" + user.getAccount() + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
			// 清除权限缓存
			myShiroRealm.getAuthorizationCache().remove(currentUser.getPrincipals());
			UserInfoVO uInfo = TokenManager.getLoginUser();
			uInfo.setCorUrl(user.getCorUrl());
			uInfo.setCompanyId(cor.getData(CompanyInfoVO.class).getCompanyId());
			return ResultUtil.getResult(RespCode.Code.SUCCESS);
		} else {
			token.clear();
			return result;
		}
	}

}
