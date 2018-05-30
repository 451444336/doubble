package com.born.controller.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.born.config.shiro.token.TokenManager;
import com.born.core.page.PageBean;
import com.born.facade.constant.MenuAuthEnum;
import com.born.facade.dto.menu.MenuChangeDTO;
import com.born.facade.dto.menu.MenuQueryDTO;
import com.born.facade.dto.permission.ChangeAuthDTO;
import com.born.facade.service.IMenuService;
import com.born.facade.service.IPermissionService;
import com.born.facade.vo.UserInfoVO;
import com.born.util.result.Result;

import lombok.extern.slf4j.Slf4j;
/**
 * 
* @ClassName: MenuController 
* @Description: 菜单控制入口 
* @author lijie 
* @date 2018年5月8日 下午6:22:58 
*
 */
@Slf4j
@Controller
@RequestMapping("web/permission/menus")
public class MenuController {

	@Reference(version = "1.0.0")
    private IMenuService menuService;

	@Reference(version = "1.0.0")
	private IPermissionService permissionService;

    /**
     * 
    * @Title: getMenuByUserId 
    * @Description: 通过用户获取菜单
    * @param @param dto
    * @param @return    设定文件 
    * @return Object    返回类型 
    * @author lijie
    * @throws
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result getMenuByUserId() {
    	UserInfoVO su = TokenManager.getLoginUser();
    	MenuQueryDTO dto = new MenuQueryDTO();
    	dto.setAscription(MenuAuthEnum.PC_MENU_AUTH.getStatus());
    	dto.setCompanyId(su.getCompanyId());
    	dto.setUserId(su.getId());
    	dto.setRoleId(su.getRoles().get(0).getId());
        Result result = menuService.getMenuTreeByUserId(dto);
        log.info("根据用户查询菜单列表树型结构返回值，result={}", JSON.toJSONString(result));
        return result;
    }
    
    /**
	 * @Description 查询菜单列表
	 * @author 黄伟
	 * @date 2018年5月2日 上午11:36:21
	 */
	@GetMapping(value = "/getMenuList")
	@ResponseBody
	public Result getMenuList() {
		//获取企业ID
		UserInfoVO su = TokenManager.getLoginUser();
		return menuService.getMenuList(su.getCompanyId());
	}
	/**
	 * 
	* @Title: addMenu 
	* @Description: 添加菜单 
	* @param @param dto
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	@ResponseBody
	@PostMapping
	public Result addMenu(MenuChangeDTO dto) {
		log.info("添加菜单数据入参={}", JSON.toJSONString(dto));
		//UserInfoVO su = TokenManager.getLoginUser();
		dto.setOperId(1L);
		dto.setCompanyId(1L);
		Result result = menuService.addMenu(dto);
		log.info("添加菜单数据返回={}", JSON.toJSONString(result));
		return result;
	}
	/**
	 * 
	* @Title: addMenuAuth 
	* @Description: 添加菜单权限数据 
	* @param @param dto
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	@ResponseBody
	@PostMapping("auth")
	public Result addMenuAuth(ChangeAuthDTO dto) {
		log.info("添加菜单权限数据入参={}", JSON.toJSONString(dto));
		//UserInfoVO su = TokenManager.getLoginUser();
		dto.setOperId(1L);
		dto.setCompanyId(1L);
		Result result = permissionService.addAuthority(dto);
		log.info("添加菜单权限数据返回={}", JSON.toJSONString(result));
		return result;
	}
	/**
	 * 
	* @Title: getMenuListByPage 
	* @Description: 根据类型分页查询菜单列表数据
	* @param @param pangeBean
	* @param @param type
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	@GetMapping("getMenuListByPage")
	@ResponseBody
	public Result getMenuListByPage(PageBean pangeBean, Byte type) {
		Result result = menuService.getMenuListByPage(pangeBean, type, TokenManager.getLoginUser().getCompanyId());
		log.info("根据类型分页查询菜单列表返回数据={}", JSON.toJSONString(result));
		return result;
	}
}
