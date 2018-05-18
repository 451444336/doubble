package com.born.controller.permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.born.config.shiro.token.TokenManager;
import com.born.facade.constant.MenuAuthEnum;
import com.born.facade.dto.permission.PermissionQueryDTO;
import com.born.facade.dto.permission.QueryMenuAuthDTO;
import com.born.facade.service.IMenuService;
import com.born.facade.service.IPermissionService;
import com.born.facade.vo.MenuVO;
import com.born.facade.vo.UserInfoVO;
import com.born.facade.vo.permission.MenuPermissionVO;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;

import lombok.extern.slf4j.Slf4j;
/**
 * 
* @ClassName: PermissionController 
* @Description: 权限数据 
* @author lijie 
* @date 2018年5月9日 下午4:59:59 
*
 */
@Slf4j
@Controller
@RequestMapping("web/user/permissions")
public class PermissionController {
	/**
	 * 权限接口
	 */
	@Reference(version = "1.0.0")
	private IPermissionService permissionService;
	
	@Reference(version = "1.0.0")
	private IMenuService menuService;
	/**
	 * 
	* @Title: loadUserPermission 
	* @Description: 加载用户权限数据
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author lijie
	* @throws
	 */
	@GetMapping("page")
	public String userPermissionPage(){
		
		return "user/qft_user_authority";
	}
	
	/**
	 * 
	* @Title: list 
	* @Description: 加载列表数据
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @author lijie
	* @throws
	 */
	@GetMapping("list")
	@SuppressWarnings("unchecked")
	public @ResponseBody Result menuAndAuths(QueryMenuAuthDTO rDto) {
		List<MenuVO> rList = new ArrayList<>();
		UserInfoVO userInfo = TokenManager.getLoginUser();
		Result mresult = menuService.getMenuTreeByCompanyId(userInfo.getCompanyId(),
				MenuAuthEnum.PC_MENU_AUTH.getStatus());
		log.info("查询菜单返回数据={}", JSON.toJSONString(mresult));
		if (mresult.isSuccess()) {
			List<MenuVO> menus = mresult.getData(List.class);
			PermissionQueryDTO dto = new PermissionQueryDTO();
			dto.setCompanyId(userInfo.getCompanyId());
			dto.setMenuIds(getMenuIds(menus, new LinkedList<>()));
			dto.setOperType(rDto.getOperType());
			dto.setPositionId(rDto.getPositionId());
			dto.setUserId(rDto.getUserId());
			List<MenuPermissionVO> list = getMenuPermissions(dto);
			Map<Long, MenuPermissionVO> map = new HashMap<>();
			for (MenuPermissionVO m : list) {
				map.put(m.getMenuId(), m);
			}
			Set<Long> haveMenus = new HashSet<>();
			if (MenuAuthEnum.PERSION_AUTH.getStatus().equals(dto.getOperType())) {
				List<MenuVO> ms = menuService.getMenuListByUserId(dto.getCompanyId(), dto.getUserId())
						.getData(List.class);
				if (CollectionUtils.isNotEmpty(ms)) {
					for (MenuVO m : ms) {
						haveMenus.add(m.getId());
					}
				}
			}
			rList = backMenuAuths(menus, map, haveMenus);
		}
		return ResultUtil.getResult(RespCode.Code.SUCCESS, rList);
	}
	/**
	 * 
	* @Title: backMenuAuths 
	* @Description: 封装菜单权限数据
	* @param @param menus
	* @param @param map
	* @param @return    设定文件 
	* @return List<MenuVO>    返回类型 
	* @author lijie
	* @throws
	 */
	private List<MenuVO> backMenuAuths(List<MenuVO> menus, Map<Long, MenuPermissionVO> map, Set<Long> haveMenus) {
		if (CollectionUtils.isNotEmpty(menus)) {
			MenuPermissionVO mv;
			for (MenuVO m : menus) {
				mv = map.get(m.getId());
				if (null == mv) {
					mv = new MenuPermissionVO();
					mv.setIsCheck(haveMenus.contains(m.getId()) ? MenuAuthEnum.CHECK.getStatus()
							: MenuAuthEnum.NOT_CHECK.getStatus());
					mv.setMenuId(m.getId());
					mv.setMenuName(m.getMenuName());
				} else {
					m.setPermission(mv);
				}
				backMenuAuths(m.getChilds(), map, haveMenus);
			}
		}
		return menus;
	}
	/**
	 * 
	* @Title: getMenuIds 
	* @Description: 得到菜单ID 
	* @param @param menus
	* @param @param result
	* @param @return    设定文件 
	* @return List<Long>    返回类型 
	* @author lijie
	* @throws
	 */
	private List<Long> getMenuIds(List<MenuVO> menus, List<Long> result) {
		for (MenuVO m : menus) {
			if (CollectionUtils.isNotEmpty(m.getChilds())) {
				getMenuIds(m.getChilds(), result);
			} else {
				result.add(m.getId());
			}
		}
		return result;
	}
	/**
	 * 
	* @Title: getMenuPermissions 
	* @Description: 查询菜单权限 
	* @param @param dto
	* @param @return    设定文件 
	* @return List<MenuPermissionVO>    返回类型 
	* @author lijie
	* @throws
	 */
	@SuppressWarnings("unchecked")
	private List<MenuPermissionVO> getMenuPermissions(PermissionQueryDTO dto){
		Result result = permissionService.getAuthorizeData(dto);
		log.info("查询权限返回数据={}",JSON.toJSONString(result));
		if(result.isSuccess()){
			return result.getData(List.class);
		}
		return new ArrayList<>();
	}
}
