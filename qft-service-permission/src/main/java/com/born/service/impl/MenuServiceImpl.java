package com.born.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.born.core.constant.CommonConstants;
import com.born.facade.constant.MenuAuthEnum;
import com.born.facade.constant.RoleEnum;
import com.born.facade.dto.menu.AddMenuDTO;
import com.born.facade.dto.menu.MenuQueryDTO;
import com.born.facade.entity.CompanyMenu;
import com.born.facade.entity.CompanyRoleMenu;
import com.born.facade.entity.MenuAuthority;
import com.born.facade.exception.PermissionException;
import com.born.facade.exception.PermissionExceptionEnum;
import com.born.facade.service.IMenuService;
import com.born.facade.vo.MenuVO;
import com.born.mapper.CompanyMenuMapper;
import com.born.mapper.CompanyRoleMenuMapper;
import com.born.mapper.MenuAuthorityMapper;
import com.born.util.ClassUtil;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
/**
 * 
* @ClassName: CompanyMenuServiceImpl 
* @Description: 菜单服务实现 
* @author lijie 
* @date 2018年4月28日 上午11:55:28 
*
 */
@Slf4j
@Service(version = "1.0.0")
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private CompanyMenuMapper companyMenuMapper;
	
	@Autowired
	private MenuAuthorityMapper menuAuthorityMapper;
	
	@Autowired
	private CompanyRoleMenuMapper companyRoleMenuMapper;
	
	@Override
	public Result getMenuTreeByUserId(MenuQueryDTO dto) {
		log.info("根据用户id查询菜单列表树型结构入参, dto={}", JSON.toJSONString(dto));
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		try {
			String errorStr = dto.validateForm();
			if (StringUtils.isNotBlank(errorStr)) {
				return ResultUtil.setResult(result, RespCode.Code.REQUEST_DATA_ERROR, errorStr);
			}
			final List<Long> roles = new ArrayList<>();
			roles.add(dto.getRoleId());
			// 根据角色ID 查询菜单
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS,
					getMenuTree(companyMenuMapper.selectMenuByCondition(roles, dto)));
		} catch (Exception e) {
			log.error("根据用户id查询菜单列表树型结构异常", e);
		}
		return result;
	}
	/**
	 * 
	* @Title: getMenuTree 
	* @Description: 获取菜单树
	* @param @param menuVos
	* @param @return    设定文件 
	* @return List<MenuVO>    返回类型 
	* @author lijie
	* @throws
	 */
	private List<MenuVO> getMenuTree(List<MenuVO> menuVos) {
		final List<MenuVO> result = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(menuVos)) {
			for (MenuVO vo : menuVos) {
				if (null != vo.getParentId() && "0".equals(vo.getParentId().toString())) {
					result.add(getChildMenu(vo, menuVos));
				}
			}
		}
		return result;
	}
	/**
	 * 
	* @Title: getChildMenu 
	* @Description: 获取子菜单
	* @param @param parent
	* @param @param menuVos
	* @param @return    设定文件 
	* @return MenuVO    返回类型 
	* @author lijie
	* @throws
	 */
	private static MenuVO getChildMenu(MenuVO parent, List<MenuVO> menuVos) {
		List<MenuVO> childs = new ArrayList<>();
		for (MenuVO vo : menuVos) {
			if (parent.getId().equals(vo.getParentId())) {
				childs.add(getChildMenu(vo, menuVos));
			}
		}
		parent.setChilds(childs);
		return parent;
	}

	@Override
	public Result getMenuList(String companyId) {
		log.info("查询所有/公司菜单数据入参={}", companyId);
		try {
			return ResultUtil.getResult(RespCode.Code.SUCCESS, companyMenuMapper.selectAllMenu(companyId, (Byte) null));
		} catch (Exception e) {
			log.error("根据公司ID 查询菜单数据异常", e);
			return ResultUtil.getResult(RespCode.Code.FAIL);
		}
	}
	
	@Override
	public Result getMenuByRoleIds(List<Long> roleIds) {
		log.info("根据角色ID查询菜单数据入参={}", JSON.toJSONString(roleIds));
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		if (CollectionUtils.isEmpty(roleIds)) {
			result.setMessage("角色ID不能为空");
			return result;
		}
		try {
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS, companyMenuMapper.selectMenuByRoleIds(roleIds));
		} catch (Exception e) {
			log.error("根据角色ID查询菜单数据异常", e);
		}
		return result;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Result addMenu(List<AddMenuDTO> list) {
		log.info("添加菜单数据入参={}", JSON.toJSONString(list));
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		try {
			String errorStr = ClassUtil.checkRequest(list);
			if (StringUtils.isNotBlank(errorStr)) {
				return ResultUtil.setResult(result, RespCode.Code.REQUEST_DATA_ERROR, errorStr);
			}
			final String companyId = list.get(0).getCompanyId();
			// 获取当前公司已存在的菜单数据
			CompanyMenu query = new CompanyMenu();
			query.setCompanyId(companyId);
			List<CompanyMenu> ms = companyMenuMapper.select(query);
			// 已存在的菜单数据信息
			Map<String, Long> existsMap = new HashMap<>();
			final Set<Long> exists = new HashSet<>();
			if (CollectionUtils.isNotEmpty(ms)) {
				for (CompanyMenu m : ms) {
					existsMap.put(m.getBaseMenuId(), m.getId());
					if (MenuAuthEnum.DELETE.getStatus().equals(m.getIsDelete())) {
						exists.add(m.getId());
					}
				}
			}
			// 存在的菜单对应的权限
			final Map<Long, List<Long>> menu_quths = new HashMap<>();
			// 得到每级菜单及对应的权限数据
			Map<Integer, List<AddMenuDTO>> levelMap = handleMenus(list, 1, new HashMap<>(), existsMap, menu_quths);
			// 不存在当前新增的菜单数据中（需要修改为删除状态）
			final List<Long> not_exists = new ArrayList<>(existsMap.values());
			for (Long id : not_exists) {
				if (exists.contains(id)) {
					exists.remove(id);
				}
			}
			changeMenuAuthority(menu_quths, companyId);
			// 修改菜单状态
			if (!exists.isEmpty()) {
				companyMenuMapper.updateMenuByAuthIds(new ArrayList<>(exists), MenuAuthEnum.NOT_DELETE.getStatus(),
						CommonConstants.SYSTEM_USER);
			}
			if (!not_exists.isEmpty()) {
				companyMenuMapper.updateMenuByAuthIds(not_exists, MenuAuthEnum.DELETE.getStatus(),
						CommonConstants.SYSTEM_USER);
			}
			if (!levelMap.isEmpty()) {
				Map<String, MenuToAuth> map = new HashMap<>();
				for (Map.Entry<Integer, List<AddMenuDTO>> entry : levelMap.entrySet()) {
					insertMenu(entry.getValue(), map, companyId);
				}
			}
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS);
		} catch (Exception e) {
			log.error("添加菜单数据异常", e);
			throw new PermissionException(PermissionExceptionEnum.ADD_MENU_ERROR);
		}
	}
	/**
	 * 
	* @Title: changeMenuAuthority 
	* @Description:处理菜单权限数据
	* @param @param menu_quths
	* @param @param companyId
	* @param @throws CloneNotSupportedException    设定文件 
	* @return void    返回类型 
	* @author lijie
	* @throws
	 */
	private void changeMenuAuthority(final Map<Long, List<Long>> menu_quths, final String companyId)
			throws CloneNotSupportedException {
		MenuAuthority record = new MenuAuthority();
		record.setCompanyId(companyId);
		menuAuthorityMapper.delete(record);
		// 保存菜单权限数据
		final MenuAuthority ma = new MenuAuthority();
		ma.setCreateTime(new Date());
		ma.setCreaterId(CommonConstants.SYSTEM_USER);
		ma.setCompanyId(companyId);
		final List<MenuAuthority> mainserts = new LinkedList<>();
		for (Map.Entry<Long, List<Long>> eny : menu_quths.entrySet()) {
			if(CollectionUtils.isEmpty(eny.getValue())){
				continue;
			}
			ma.setMenuId(eny.getKey());
			for (Long authId : eny.getValue()) {
				ma.setAuthorityId(authId);
				mainserts.add(ma.clone());
			}
		}
		if(CollectionUtils.isNotEmpty(mainserts)){
			menuAuthorityMapper.insertList(mainserts);
		}
	}
	/**
	 * 
	 * @ClassName: BaseToMenu
	 * @Description: 菜单主键值映射
	 * @author: lijie
	 * @date: 2018年5月5日 下午6:29:41
	 */
	@Data
	protected class MenuToAuth {
		/**
		 * 菜单/权限基础数据
		 */
		private String authorityBaseId;
		/**
		 * 菜单ID
		 */
		private Long menuId;
		/**
		 * 权限ID
		 */
		private List<Long> auths;
		/**
		 * 创建人ID
		 */
		private Long createrId;

		public MenuToAuth(String authorityBaseId, Long menuId, List<Long> auths, Long createrId) {
			this.authorityBaseId = authorityBaseId;
			this.menuId = menuId;
			this.auths = auths;
			this.createrId = createrId;
		}
	}
	/**
	 * 
	* @Title: insertMenu  
	* @Description: 新增菜单/菜单权限数据 
	* @param: @param list
	* @param: @param map
	* @param: @throws CloneNotSupportedException
	* @return void
	* @author lijie
	* @throws
	 */
	private void insertMenu(List<AddMenuDTO> list, Map<String, MenuToAuth> map, String companyId)
			throws CloneNotSupportedException {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		final CompanyMenu cm = new CompanyMenu();
		final List<CompanyMenu> inserts = new LinkedList<>();
		cm.setCreateTime(new Date());
		cm.setIsDelete(MenuAuthEnum.NOT_DELETE.getStatus());
		cm.setIsUsable(MenuAuthEnum.AVAILABLE.getStatus());
		MenuToAuth get;
		MenuToAuth save;
		final Map<String, MenuToAuth> copyMap = new HashMap<>();
		for (AddMenuDTO add : list) {
			cm.setBaseMenuId(add.getMenuId());
			cm.setAppSeq(add.getAppSeq());
			cm.setAppUrl(add.getAppUrl());
			cm.setAscription(add.getAscription());
			cm.setCompanyId(add.getCompanyId());
			cm.setCreaterId(add.getCreaterId());
			cm.setIcon(add.getIcon());
			cm.setMenuName(add.getMenuName());
			cm.setMenuSeq(add.getMenuSeq());
			cm.setMenuUrl(add.getMenuUrl());
			cm.setType(add.getType());
			get = map.get(add.getParentId());
			if (null == get) {
				cm.setParentId(0L);
			} else {
				cm.setParentId(get.getMenuId());
			}
			save = new MenuToAuth(add.getMenuId(), null, add.getAuths(), add.getCreaterId());
			map.put(add.getMenuId(), save);
			copyMap.put(add.getMenuId(), save);
			inserts.add(cm.clone());
		}
		final List<MenuToAuth> authMenus = new LinkedList<>();
		final List<Long> menuIds = new ArrayList<>(inserts.size());
		// 保存菜单数据
		companyMenuMapper.insertList(inserts);
		for (CompanyMenu cms : inserts) {
			menuIds.add(cms.getId());
			save = copyMap.get(cms.getBaseMenuId());
			save.setMenuId(cms.getId());
			if (CollectionUtils.isNotEmpty(save.getAuths())) {
				authMenus.add(save);
			}
		}
		// 为超级管理设置菜单
		insertRoleMenu(menuIds, RoleEnum.ADMIN.getId());
		if (!authMenus.isEmpty()) {
			// 保存菜单权限数据
			final MenuAuthority ma = new MenuAuthority();
			ma.setCreateTime(new Date());
			ma.setCompanyId(companyId);
			final List<MenuAuthority> mainserts = new LinkedList<>();
			for (MenuToAuth mta : authMenus) {
				ma.setCreaterId(mta.getCreaterId());
				ma.setMenuId(mta.getMenuId());
				for (Long authId : mta.getAuths()) {
					ma.setAuthorityId(authId);
					mainserts.add(ma.clone());
				}
			}
			menuAuthorityMapper.insertList(mainserts);
		}
	}
	/**
	 * @throws CloneNotSupportedException 
	* @Title: insertRoleMenu 
	* @Description: 为角色分配菜单 
	* @param @param menuIds
	* @param @param roleId    设定文件 
	* @return void    返回类型 
	* @author lijie
	* @throws
	 */
	private void insertRoleMenu(final List<Long> menuIds, final Long roleId) throws CloneNotSupportedException {
		if (CollectionUtils.isNotEmpty(menuIds)) {
			final List<CompanyRoleMenu> recordList = new ArrayList<>(menuIds.size());
			final CompanyRoleMenu mr = new CompanyRoleMenu();
			mr.setCreaterId(CommonConstants.SYSTEM_USER);
			mr.setCreateTime(new Date());
			mr.setRoleId(roleId);
			for (Long m : menuIds) {
				mr.setMenuId(m);
				recordList.add(mr.clone());
			}
			companyRoleMenuMapper.insertList(recordList);
		}
	}
	/**
	 * 
	* @Title: handleMenus 
	* @Description: 封装每级的菜单数据
	* @param @param list
	* @param @param key
	* @param @param level_map
	* @param @param existsMap
	* @param @return    设定文件 
	* @return Map<Integer,List<AddMenuDTO>>    返回类型 
	* @author lijie
	* @throws
	 */
	private Map<Integer, List<AddMenuDTO>> handleMenus(final List<AddMenuDTO> list, int key,
			final Map<Integer, List<AddMenuDTO>> levelMap, final Map<String, Long> existsMap,
			final Map<Long, List<Long>> menu_quths) {
		List<AddMenuDTO> result;
		AddMenuDTO info;
		for (AddMenuDTO amd : list) {
			info = new AddMenuDTO();
			BeanUtils.copyProperties(amd, info);
			info.setNexts(null);
			result = levelMap.get(key);
			if (null == result) {
				result = new LinkedList<AddMenuDTO>();
				levelMap.put(key, result);
			}
			if (null == existsMap.get(amd.getMenuId())) {
				result.add(info);
			} else {
				menu_quths.put(existsMap.get(amd.getMenuId()), amd.getAuths());
				existsMap.remove(amd.getMenuId());
			}
			if (CollectionUtils.isNotEmpty(amd.getNexts())) {
				handleMenus(amd.getNexts(), ++key, levelMap, existsMap, menu_quths);
			}
		}
		return levelMap;
	}

	@Override
	public Result getMenuTreeByCompanyId(String companyId, Byte ascription) {
		log.info("根据公司id查询菜单列表树型结构入参, companyId={},ascription={}", companyId, ascription);
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		if (StringUtils.isBlank(companyId)) {
			return ResultUtil.setResult(result, RespCode.Code.REQUEST_DATA_ERROR, 
					"公司ID不能为空");
		}
		if (null == ascription) {
			return ResultUtil.setResult(result, RespCode.Code.REQUEST_DATA_ERROR, 
					"菜单归属类型不能为空[pc/app]");
		}
		try {
			List<MenuVO> list = getMenuTree(companyMenuMapper.selectAllMenu(companyId, ascription));
			if (CollectionUtils.isNotEmpty(list)) {
				return ResultUtil.setResult(result, RespCode.Code.SUCCESS, list);
			}
		} catch (Exception e) {
			log.error("根据公司id查询菜单列表树型结构异常", e);
		}
		return result;
	}
	
	@Override
	public Result getSubmenuMenuById(Long menuId) {
		log.info("查询子菜单数据入参={}", menuId);
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		if (null == menuId) {
			return ResultUtil.setResult(result, RespCode.Code.REQUEST_DATA_ERROR, "菜单ID不能为空");
		}
		try {
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS, 
					companyMenuMapper.selectMenuSubmenuById(menuId));
		} catch (Exception e) {
			log.error("查询子菜单数据异常", e);
		}
		return result;
	}
}
