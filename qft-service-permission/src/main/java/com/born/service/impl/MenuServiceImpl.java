package com.born.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.born.core.base.BaseModel;
import com.born.core.base.BaseService;
import com.born.core.base.DataBaseParameters;
import com.born.core.constant.CommonConstants;
import com.born.core.constant.DataBaseEnum;
import com.born.core.page.PageBean;
import com.born.entity.CompanyAuthority;
import com.born.entity.CompanyMenu;
import com.born.entity.CompanyRoleMenu;
import com.born.entity.Menu;
import com.born.facade.constant.MenuAuthEnum;
import com.born.facade.constant.RoleEnum;
import com.born.facade.dto.menu.DistributionMenuAuthDTO;
import com.born.facade.dto.menu.MenuChangeDTO;
import com.born.facade.dto.menu.MenuQueryDTO;
import com.born.facade.exception.PermissionException;
import com.born.facade.exception.PermissionExceptionEnum;
import com.born.facade.service.IMenuService;
import com.born.facade.vo.MenuVO;
import com.born.mapper.CompanyAuthorityMapper;
import com.born.mapper.CompanyMenuMapper;
import com.born.mapper.CompanyRoleMenuMapper;
import com.born.mapper.MenuMapper;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

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
public class MenuServiceImpl extends BaseService<BaseModel, Menu> implements IMenuService {

	@Autowired
	private CompanyMenuMapper companyMenuMapper;
	
	@Autowired
	private CompanyAuthorityMapper authorityMapper;
	
	@Autowired
	private CompanyRoleMenuMapper companyRoleMenuMapper;
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public Result getMenuTreeByUserId(MenuQueryDTO dto) {
		log.info("根据用户id查询菜单列表树型结构入参, dto={}", JSON.toJSONString(dto));
		try {
			String errorStr = dto.validateForm();
			if (StringUtils.isNotBlank(errorStr)) {
				return ResultUtil.requestDataError(errorStr);
			}
			final List<Long> roles = new ArrayList<>();
			roles.add(dto.getRoleId());
			// 根据角色ID 查询菜单
			return ResultUtil.success(getMenuTree(companyMenuMapper.selectMenuByCondition(roles, dto)));
		} catch (Exception e) {
			log.error("根据用户id查询菜单列表树型结构异常", e);
			return ResultUtil.serverError();
		}
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
	public Result getMenuByRoleIds(List<Long> roleIds, Long companyId) {
		log.info("根据角色ID查询菜单数据入参={},companyId={}", JSON.toJSONString(roleIds), companyId);
		if (CollectionUtils.isEmpty(roleIds)) {
			return ResultUtil.requestDataError("角色ID不能为空");
		}
		if (null == companyId) {
			return ResultUtil.requestDataError("公司ID不能为空");
		}
		try {
			return ResultUtil.success(companyMenuMapper.selectMenuByRoleIds(roleIds, companyId));
		} catch (Exception e) {
			log.error("根据角色ID查询菜单数据异常", e);
			return ResultUtil.serverError();
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
	private void insertRoleMenu(final List<Long> menuIds, final Long roleId, final Long companyId)
			throws CloneNotSupportedException {
		if (CollectionUtils.isNotEmpty(menuIds)) {
			final List<CompanyRoleMenu> recordList = new ArrayList<>(menuIds.size());
			final CompanyRoleMenu mr = new CompanyRoleMenu();
			mr.setCompanyId(companyId);
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
	

	@Override
	public Result getMenuTreeByCompanyId(Long companyId, Byte ascription) {
		log.info("根据公司id查询菜单列表树型结构入参, companyId={},ascription={}", companyId, ascription);
		if (null == companyId) {
			return ResultUtil.requestDataError("公司ID不能为空");
		}
		if (null == ascription) {
			return ResultUtil.requestDataError("菜单归属类型不能为空[pc/app]");
		}
		try {
			List<MenuVO> list = getMenuTree(companyMenuMapper.selectAllMenu(companyId, ascription));
			if (CollectionUtils.isNotEmpty(list)) {
				return ResultUtil.success(list);
			}
			return ResultUtil.fail(RespCode.Code.NOT_QUERY_DATA);
		} catch (Exception e) {
			log.error("根据公司id查询菜单列表树型结构异常", e);
			return ResultUtil.serverError();
		}
	}
	
	@Override
	public Result getSubmenuMenuById(Long menuId) {
		log.info("查询子菜单数据入参={}", menuId);
		if (null == menuId) {
			return ResultUtil.requestDataError("菜单ID不能为空");
		}
		try {
			return ResultUtil.success(companyMenuMapper.selectMenuSubmenuById(menuId));
		} catch (Exception e) {
			log.error("查询子菜单数据异常", e);
			return ResultUtil.serverError();
		}
	}
	
	
	@Override
	public Result getMenuListByUserId(Long companyId, Long userId) {
		log.info("根据用户id菜单数据入参, companyId={},userId={}", companyId, userId);
		try {
			// 根据用户ID 查询菜单
			return ResultUtil.success(companyMenuMapper.selectMenuByUserId(userId, companyId));
		} catch (Exception e) {
			log.error("根据用户id菜单数据异常", e);
			return ResultUtil.serverError();
		}
	}
	
	@Override
	protected DataBaseParameters<Menu> getDataBaseParameters() {
		DataBaseParameters<Menu> dataBase = new DataBaseParameters<>();
		dataBase.setEntityClass(Menu.class);
		dataBase.setMapper(menuMapper);
		return dataBase;
	}
	
	@Override
	@Transactional
	public Result addMenu(MenuChangeDTO dto) {
		log.info("添加菜单ID入参={}", JSON.toJSONString(dto));
		String errorStr = dto.validateForm();
		if (StringUtils.isNotBlank(errorStr)) {
			return ResultUtil.fail(RespCode.Code.REQUEST_DATA_ERROR, errorStr);
		}
		try {
			if (checkMenuCode(dto.getMenuCode(), dto.getMenuId())) {
				return ResultUtil.fail(PermissionExceptionEnum.CODE_EXISTS);
			}
			Menu record = initMenu(dto);
			// 菜单存在则修改，不存在则新增
			if (null == dto.getMenuId()) {
				if (null == dto.getParentId()) {
					record.setParentId(0L);
					record.setMenuLevel(1);
				} else {
					Menu paerent = menuMapper.selectByPrimaryKey(dto.getParentId());
					if (null == paerent || DataBaseEnum.DELETE.getStatus().equals(paerent.getIsDelete())) {
						return ResultUtil.fail(PermissionExceptionEnum.PARENT_MENU_NOTEXISTS);
					}
					record.setParentId(paerent.getId());
					record.setMenuLevel(paerent.getMenuLevel() + 1);
				}
				record.setCreaterId(dto.getOperId());
				record.setCreateTime(new Date());
				record.setIsDelete(DataBaseEnum.NOT_DELETE.getStatus());
				record.setIsUsable(MenuAuthEnum.AVAILABLE.getStatus());
				menuMapper.insertUseGeneratedKeys(record);
				List<Long> roleMenus = Lists.newArrayList();
				roleMenus.add(record.getId());
				insertRoleMenu(roleMenus, RoleEnum.ADMIN.getId(), dto.getCompanyId());
			} else {
				record.setId(dto.getMenuId());
				record.setUpdaterId(dto.getOperId());
				record.setUpdateTime(new Date());
				menuMapper.updateByPrimaryKeySelective(record);
			}
			// 新增公司关系记录
			insertCompanyMenu(dto.getCompanyId(), dto.getOperId(), record.getId());
			return ResultUtil.success();
		} catch (Exception e) {
			log.error("添加菜单数据异常", e);
			throw new PermissionException(PermissionExceptionEnum.ADD_MENU_ERROR);
		}
	}
	/**
	 * 
	* @Title: initMenu 
	* @Description: 初始化 
	* @param @param dto
	* @param @return    设定文件 
	* @return Menu    返回类型 
	* @author lijie
	* @throws
	 */
	private Menu initMenu(MenuChangeDTO dto) {
		Menu result = new Menu();
		result.setMenuCode(dto.getMenuCode());
		result.setMenuName(dto.getMenuName());
		result.setMenuUrl(dto.getMenuUrl());
		result.setAscription(dto.getAscription());
		result.setType(dto.getType());
		result.setMenuSeq(dto.getMenuSeq());
		return result;
	}
	/**
	 * 
	* @Title: insertCompanyMenu 
	* @Description: 新增公司菜单关系记录 
	* @param @param companyId
	* @param @param createrId
	* @param @param createTime
	* @param @param menuId
	* @param @return    设定文件 
	* @return int    返回类型 
	* @author lijie
	* @throws
	 */
	private int insertCompanyMenu(final Long companyId, final Long createrId, final Long menuId) {
		CompanyMenu cm = new CompanyMenu();
		cm.setCompanyId(companyId);
		cm.setCreaterId(createrId);
		cm.setCreateTime(new Date());
		cm.setMenuId(menuId);
		cm.setIsDelete(DataBaseEnum.NOT_DELETE.getStatus());
		return companyMenuMapper.insert(cm);
	}
	/**
	 * 
	* @Title: checkMenuCode 
	* @Description: 校验编码是否存在 
	* @param @param menuCode
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author lijie
	* @throws
	 */
	private boolean checkMenuCode(String menuCode, Long menuId) {
		Menu record = new Menu();
		record.setMenuCode(menuCode);
		record = menuMapper.selectOne(record);
		return null != record && !record.getId().equals(menuId);
	}
	
	@Override
	@Transactional
	public Result addCompanyMenuAuth(DistributionMenuAuthDTO dto) {
		log.info("添加公司菜单权限数据入参={}", JSON.toJSONString(dto));
		String errorStr = dto.validateForm();
		if (StringUtils.isNotBlank(errorStr)) {
			return ResultUtil.requestDataError(errorStr);
		}
		try {
			handleAuths(dto);
			handleMenus(dto);
			return ResultUtil.success();
		} catch (Exception e) {
			log.error("添加公司菜单权限数据异常", e);
			throw new PermissionException(PermissionExceptionEnum.ADD_MENU_ERROR);
		}
	}
	/**
	 * @throws CloneNotSupportedException 
	* @Title: handleAuths 
	* @Description: 包装菜单权限处理数据 
	* @param @param dto
	* @param @return    设定文件 
	* @return Map<MenuOperEnum,Object>    返回类型 
	* @author lijie
	* @throws
	 */
	private void handleAuths(DistributionMenuAuthDTO dto) throws CloneNotSupportedException {
		if (CollectionUtils.isEmpty(dto.getAuthIds())) {
			return;
		}
		CompanyAuthority record = new CompanyAuthority();
		record.setCompanyId(dto.getCompanyId());
		final List<CompanyAuthority> list = authorityMapper.select(record);
		final Map<Long, CompanyAuthority> existsAuths = Maps.newHashMap();
		final Set<Long> checkMenus = Sets.newHashSet();
		if (CollectionUtils.isNotEmpty(list)) {
			for (CompanyAuthority c : list) {
				existsAuths.put(c.getAuthorityId(), c);
				checkMenus.add(c.getAuthorityId());
			}
		}
		final List<Long> authIds = dto.getAuthIds();
		final List<CompanyAuthority> inserts = Lists.newArrayList();
		final List<Long> updateExists = Lists.newArrayList();
		final CompanyAuthority ca = new CompanyAuthority();
		ca.setCompanyId(dto.getCompanyId());
		ca.setIsDelete(DataBaseEnum.NOT_DELETE.getStatus());
		ca.setCreaterId(dto.getOperatorId());
		ca.setCreateTime(new Date());
		CompanyAuthority exAuth;
		for (Long authId : authIds) {
			if (checkMenus.contains(authId)) {
				exAuth = existsAuths.get(authId);
				if (null != exAuth) {
					if (DataBaseEnum.DELETE.getStatus().equals(exAuth.getIsDelete())) {
						updateExists.add(authId);
					}
					existsAuths.remove(authId);
				}
			} else {
				ca.setAuthorityId(authId);
				inserts.add(ca.clone());
			}
		}
		if (!inserts.isEmpty()) {
			authorityMapper.insertList(inserts);
		}
		if (!updateExists.isEmpty()) {
			authorityMapper.updateMenuAuthorityByAuthIds(updateExists, DataBaseEnum.NOT_DELETE.getStatus(),
					dto.getOperatorId());
		}
		if (!existsAuths.isEmpty()) {
			authorityMapper.updateMenuAuthorityByAuthIds(Lists.newArrayList(existsAuths.keySet()),
					DataBaseEnum.DELETE.getStatus(), dto.getOperatorId());
		}
	}
	/**
	 * 
	* @Title: handleMenus 
	* @Description:包装菜单数据
	* @param @param dto
	* @param @return
	* @param @throws CloneNotSupportedException    设定文件 
	* @return Map<MenuOperEnum,Object>    返回类型 
	* @author lijie
	* @throws
	 */
	private void handleMenus(DistributionMenuAuthDTO dto) throws CloneNotSupportedException {
		if (CollectionUtils.isEmpty(dto.getMenuIds())) {
			return;
		}
		CompanyMenu record = new CompanyMenu();
		record.setCompanyId(dto.getCompanyId());
		final List<CompanyMenu> list = companyMenuMapper.select(record);
		final Map<Long, CompanyMenu> existsMenus = Maps.newHashMap();
		final Set<Long> checkMenus = Sets.newHashSet();
		if (CollectionUtils.isNotEmpty(list)) {
			for (CompanyMenu c : list) {
				existsMenus.put(c.getMenuId(), c);
				checkMenus.add(c.getMenuId());
			}
		}
		final List<Long> menuIds = dto.getMenuIds();
		final List<CompanyMenu> inserts = Lists.newArrayList();
		final List<Long> updateExists = Lists.newArrayList();
		final CompanyMenu cm = new CompanyMenu();
		cm.setCompanyId(dto.getCompanyId());
		cm.setIsDelete(DataBaseEnum.NOT_DELETE.getStatus());
		cm.setCreaterId(dto.getOperatorId());
		cm.setCreateTime(new Date());
		CompanyMenu exMenu;
		for (Long menuId : menuIds) {
			if (checkMenus.contains(menuId)) {
				exMenu = existsMenus.get(menuId);
				if (null != exMenu) {
					if (DataBaseEnum.DELETE.getStatus().equals(exMenu.getIsDelete())) {
						updateExists.add(menuId);
					}
					existsMenus.remove(menuId);
				}
			} else {
				cm.setMenuId(menuId);
				inserts.add(cm.clone());
			}
		}
		if (!inserts.isEmpty()) {
			companyMenuMapper.insertList(inserts);
			List<Long> roleMenus = Lists.newArrayList();
			for (CompanyMenu rcm : inserts) {
				roleMenus.add(rcm.getMenuId());
			}
			insertRoleMenu(roleMenus, RoleEnum.ADMIN.getId(), dto.getCompanyId());
		}
		if (!updateExists.isEmpty()) {
			companyMenuMapper.updateMenuByAuthIds(updateExists, DataBaseEnum.NOT_DELETE.getStatus(),
					dto.getOperatorId());
		}
		if (!existsMenus.isEmpty()) {
			companyMenuMapper.updateMenuByAuthIds(Lists.newArrayList(existsMenus.keySet()),
					DataBaseEnum.DELETE.getStatus(), dto.getOperatorId());
		}
	}
	
	@Override
	public Result getMenuList(Long companyId) {
		log.info("查询所有/公司菜单数据入参={}", companyId);
		try {
			return ResultUtil.success(companyMenuMapper.selectAllMenu(companyId, (Byte) null));
		} catch (Exception e) {
			log.error("根据公司ID 查询菜单数据异常", e);
			return ResultUtil.serverError();
		}
	}
	
	@Override
	public Result getMenuListByPage(PageBean pangeBean, Byte type, Long companyId) {
		log.info("根据类型分页查询菜单列表数据入参={},type={},companyId={}", JSON.toJSONString(pangeBean), type, companyId);
		try {
			PageHelper.startPage(pangeBean.getPageNum(), pangeBean.getPageSize());
			List<MenuVO> list = companyMenuMapper.selectAllMenu(companyId, type);
			PageInfo<MenuVO> pageInfo = new PageInfo<>(list);
			return ResultUtil.success(pageInfo.getList(), pageInfo.getTotal());
		} catch (Exception e) {
			log.error("根据类型分页查询菜单列表数据异常", e);
			return ResultUtil.serverError();
		}
	}
}
