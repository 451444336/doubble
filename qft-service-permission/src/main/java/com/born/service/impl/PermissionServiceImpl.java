package com.born.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.born.core.constant.CommonConstants;
import com.born.entity.AuthorityChange;
import com.born.entity.CompanyAuthority;
import com.born.entity.CompanyMenu;
import com.born.entity.MenuAuthority;
import com.born.entity.UserAuthority;
import com.born.facade.constant.AuthChangeEnum;
import com.born.facade.constant.MenuAuthEnum;
import com.born.facade.dto.menu.AddMenuDTO;
import com.born.facade.dto.permission.AddPermissionDTO;
import com.born.facade.dto.permission.MenuDTO;
import com.born.facade.dto.permission.PermissionInfoDTO;
import com.born.facade.dto.permission.PermissionQueryDTO;
import com.born.facade.exception.PermissionException;
import com.born.facade.exception.PermissionExceptionEnum;
import com.born.facade.service.ICompanyRoleService;
import com.born.facade.service.IMenuService;
import com.born.facade.service.IPermissionService;
import com.born.facade.vo.RoleVO;
import com.born.facade.vo.company.CompanyInfoVO;
import com.born.mapper.AuthorityChangeMapper;
import com.born.mapper.CompanyAuthorityMapper;
import com.born.mapper.CompanyMenuMapper;
import com.born.mapper.CompanyRoleMapper;
import com.born.mapper.MenuAuthorityMapper;
import com.born.mapper.UserAuthorityMapper;
import com.born.service.impl.extend.MenuPermissionFactory;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;

import lombok.extern.slf4j.Slf4j;
/**
 * 
* @ClassName: PermissionServiceImpl  
* @Description: 权限服务实现  
* @author lijie
* @date 2018年4月25日  
*
 */
@Slf4j
@Service(version = "1.0.0")
public class PermissionServiceImpl implements IPermissionService {

	@Autowired
	private CompanyAuthorityMapper companyAuthorityMapper;
	
	@Autowired
	private IMenuService menuService;
	
	@Autowired
	private AuthorityChangeMapper authorityChangeMapper;
	
	@Autowired
	private UserAuthorityMapper staffAuthorityMapper;
	
	@Autowired
	private MenuAuthorityMapper menuAuthorityMapper;
	
	@Autowired
	private CompanyMenuMapper companyMenuMapper;
	
	@Autowired
	private MenuPermissionFactory menuPermissionFactory;
	
	@Autowired
	private CompanyRoleMapper companyRoleMapper;
	
	@Autowired
	private ICompanyRoleService companyRoleService;
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public Result addPermission(AddPermissionDTO dto) {
		log.info("权限添加入参={}", JSON.toJSONString(dto));
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		String errorStr = dto.validateForm();
		if (StringUtils.isNotBlank(errorStr)) {
			return ResultUtil.setResult(result, RespCode.Code.REQUEST_DATA_ERROR, errorStr);
		}
		try {
			// 所有的菜单权限数据包括已存在的
			Map<String, List<Long>> menu_auth_map = new HashMap<>();
			if (CollectionUtils.isNotEmpty(dto.getAuths())) {
				// 封装权限菜单基础数据并添加权限数据
				Map<AuthOperEnum, Object> map = initMenuAuthority(dto.getAuths(), dto.getTemplateId(),
						dto.getCompanyId(), menu_auth_map);
				List<CompanyAuthority> addAuths = (List<CompanyAuthority>) map.get(AuthOperEnum.ADD_AUTH);
				// 新增权限数据
				if (insertMenuAuthorityBase(addAuths)) {
					List<Long> auths;
					for (CompanyAuthority auth : addAuths) {
						auths = menu_auth_map.get(auth.getBaseMenuId());
						if (null == auths) {
							auths = new LinkedList<>();
							menu_auth_map.put(auth.getBaseMenuId(), auths);
						}
						auths.add(auth.getId());
					}
				}
				// 修改数据状态（存在/不存在的权限数据）
				updateMenuAuthorityBase(map);
			}
			// 新增权限变更记录表数据
			insertAuthorityChange(dto);
			// 封装菜单数据并添加菜单
			List<AddMenuDTO> menus = packAddMenus(dto.getMenus(), menu_auth_map, dto.getCompanyId());
			if (CollectionUtils.isEmpty(menus) && menu_auth_map.size() > 0) {
				// 没有菜单数据只给菜单添加权限数据
				insertMenuAuthority(menu_auth_map, dto.getCompanyId());
			} else {
				Result addMenuResult = menuService.addMenu(menus);
				log.info("添加菜单返回数据={}", JSON.toJSONString(addMenuResult));
				if (!RespCode.Code.SUCCESS.getCode().equals(addMenuResult.getCode())) {
					throw new PermissionException(PermissionExceptionEnum.ADD_MENU_ERROR);
				}
			}
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS);
		} catch (Exception e) {
			log.error("权限添加异常", e);
			throw new PermissionException(PermissionExceptionEnum.ADD_PERMISSION_ERROR);
		}
	}
	/**
	 * @throws CloneNotSupportedException 
	* @Title: insertMenuAuthority 
	* @Description: 添加菜单权限数据 
	* @param @param menu_auth_map    设定文件 
	* @return void    返回类型 
	* @author lijie
	* @throws
	 */
	private void insertMenuAuthority(Map<String, List<Long>> menu_auth_map, String companyId)
			throws CloneNotSupportedException {
		CompanyMenu record = new CompanyMenu();
		CompanyMenu result;
		final List<MenuAuthority> inserts = new ArrayList<>();
		final MenuAuthority inset = new MenuAuthority();
		inset.setCreaterId(CommonConstants.SYSTEM_USER);
		inset.setCreateTime(new Date());
		inset.setCompanyId(companyId);
		List<Long> auths;
		for (Entry<String, List<Long>> en : menu_auth_map.entrySet()) {
			record.setBaseMenuId(en.getKey());
			auths = en.getValue();
			result = companyMenuMapper.selectOne(record);
			if (null != result) {
				for (Long id : auths) {
					inset.setAuthorityId(id);
					inset.setMenuId(result.getId());
					inserts.add(inset.clone());
				}
			}
		}
		if (!inserts.isEmpty()) {
			MenuAuthority delete = new MenuAuthority();
			delete.setCompanyId(companyId);
			// 先删除后新增
			menuAuthorityMapper.delete(delete);
			menuAuthorityMapper.insertList(inserts);
		}
	}
	/**
	 * @throws CloneNotSupportedException 
	* @Title: insertAuthorityChange 
	* @Description: 新增权限变更记录表数据 
	* @param @param dto    设定文件 
	* @return void    返回类型 
	* @author lijie
	* @throws
	 */
	private void insertAuthorityChange(AddPermissionDTO dto) throws CloneNotSupportedException {
		if (CollectionUtils.isEmpty(dto.getMenus()) && CollectionUtils.isEmpty(dto.getAuths())) {
			return;
		}
		final AuthorityChange change = initAuthorityChange(dto);
		final List<MenuDTO> menus = dto.getMenus();
		final List<PermissionInfoDTO> auths = dto.getAuths();
		final List<AuthorityChange> recordList = new LinkedList<>();
		if (CollectionUtils.isNotEmpty(menus)) {
			for (MenuDTO auth : menus) {
				change.setAuthorityId(auth.getId());
				recordList.add(change.clone());
			}
		}
		if (CollectionUtils.isNotEmpty(auths)) {
			for (PermissionInfoDTO auth : auths) {
				change.setAuthorityId(auth.getAuthId());
				recordList.add(change.clone());
			}
		}
		if (!recordList.isEmpty()) {
			authorityChangeMapper.insertList(recordList);
		}
	}
	/**
	 * 
	* @Title: initAuthorityChange 
	* @Description: 初始化权限变更数据 
	* @param @param dto
	* @param @return    设定文件 
	* @return AuthorityChange    返回类型 
	* @author lijie
	* @throws
	 */
	private AuthorityChange initAuthorityChange(AddPermissionDTO dto){
		final AuthorityChange result = new AuthorityChange();
		result.setCompanyId(dto.getCompanyId());
		result.setCreaterId(CommonConstants.SYSTEM_USER);
		result.setCreateTime(new Date());
		result.setOldUserId(dto.getUserId());
		result.setOperType(AuthChangeEnum.ADD.getStatus());
		result.setTemplateId(dto.getTemplateId());
		return result;
	}
	/**
	 * 
	* @Title: updateMenuAuthorityBase 
	* @Description: 修改权限基础数据存在/不存在的删除状态 
	* @param @param map    设定文件 
	* @return void    返回类型 
	* @author lijie
	* @throws
	 */
	@SuppressWarnings("unchecked")
	private void updateMenuAuthorityBase(Map<AuthOperEnum, Object> map) {
		List<Long> not_exists = (List<Long>) map.get(AuthOperEnum.UPDATE_NOT_EXISTS);
		if (CollectionUtils.isNotEmpty(not_exists)) {
			// 修改不在新增范围之内菜单为删除状态
			companyAuthorityMapper.updateMenuAuthorityByAuthIds(not_exists, MenuAuthEnum.DELETE.getStatus(),
					CommonConstants.SYSTEM_USER);
		}

		List<Long> exists = (List<Long>) map.get(AuthOperEnum.UPDATE_EXISTS);
		if (CollectionUtils.isNotEmpty(exists)) {
			// 修改新增范围之内菜单为正常状态
			companyAuthorityMapper.updateMenuAuthorityByAuthIds(exists, MenuAuthEnum.NOT_DELETE.getStatus(),
					CommonConstants.SYSTEM_USER);
		}
	}
	/**
	 * 
	* @Title: insertMenuAuthorityBase 
	* @Description: 新增权限基础数据 
	* @param @param addBases
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @author lijie
	* @throws
	 */
	private boolean insertMenuAuthorityBase(List<CompanyAuthority> addAuths){
		if (CollectionUtils.isNotEmpty(addAuths)) {
			companyAuthorityMapper.insertList(addAuths);
			return true;
		}
		return false;
	}
	/**
	 * 
	* @ClassName: AuthOperEnum 
	* @Description:权限操作相关枚举
	* @author lijie 
	* @date 2018年5月4日 下午12:03:20 
	*
	 */
	protected enum AuthOperEnum {
		/**
		 * 新增权限标记
		 */
		ADD_AUTH,
		/**
		 * 修改不存在的数据标记
		 */
		UPDATE_NOT_EXISTS,
		/**
		 * 修改存在的数据标记
		 */
		UPDATE_EXISTS
		;
	}
	/**
	 * 
	* @Title: initMenuAuthorityBase 
	* @Description: 封装菜单/权限基础数据 
	* @param @param list
	* @param @param templateId
	* @param @return
	* @param @throws CloneNotSupportedException    设定文件 
	* @return List<MenuAuthorityBase>    返回类型 
	* @author lijie
	* @throws
	 */
	private Map<AuthOperEnum, Object> initMenuAuthority(final List<PermissionInfoDTO> auths, final String templateId,
			final String companyId, final Map<String, List<Long>> menu_auth_map) throws CloneNotSupportedException {
		final Map<AuthOperEnum, Object> resultMap = new HashMap<>();
		// 查询已存在的权限数据
		final List<CompanyAuthority> alllist = getAllAuthorityByCompanyId(companyId);
		final Map<String, CompanyAuthority> existsMap = new HashMap<String, CompanyAuthority>(alllist.size());
		final Set<String> checkSet = new HashSet<>(alllist.size());
		if (CollectionUtils.isNotEmpty(alllist)) {
			for (CompanyAuthority base : alllist) {
				existsMap.put(base.getBaseAuthorityId(), base);
				checkSet.add(base.getBaseAuthorityId());
			}
		}

		// 需要添加的权限数据
		final List<CompanyAuthority> adds = new LinkedList<>();

		// 变更后存在的数据/需要修改删除状态的数据
		final List<Long> exists = new LinkedList<>();
		// 变更后不存在的数据/需要修改为删除状态的数据
		final List<Long> not_exists = new LinkedList<>();

		CompanyAuthority insert = new CompanyAuthority();
		insert.setIsDelete(MenuAuthEnum.NOT_DELETE.getStatus());
		insert.setIsUsable(MenuAuthEnum.AVAILABLE.getStatus());
		insert.setCompanyId(companyId);
		insert.setCreateTime(new Date());
		insert.setCreaterId(CommonConstants.SYSTEM_USER);
		// 存在则忽略，不存在则添加
		CompanyAuthority auth;
		List<Long> authIds;
		for (PermissionInfoDTO dto : auths) {
			if (!checkSet.contains(dto.getAuthId())) {
				getMenuAuthority(insert, dto);
				adds.add(insert.clone());
			} else {
				auth = existsMap.get(dto.getAuthId());
				if (null == auth) {
					continue;
				}
				if (MenuAuthEnum.DELETE.getStatus().equals(auth.getIsDelete())) {
					exists.add(auth.getId());
				}
				authIds = menu_auth_map.get(dto.getMenuId());
				if (null == authIds) {
					authIds = new LinkedList<>();
					menu_auth_map.put(dto.getMenuId(), authIds);
				}
				authIds.add(auth.getId());
				existsMap.remove(dto.getAuthId());
			}
		}
		Collection<CompanyAuthority> not_exists_base = existsMap.values();
		if (not_exists_base.size() > 0) {
			for (CompanyAuthority neb : not_exists_base) {
				not_exists.add(neb.getId());
			}
		}
		resultMap.put(AuthOperEnum.ADD_AUTH, adds);
		resultMap.put(AuthOperEnum.UPDATE_EXISTS, exists);
		resultMap.put(AuthOperEnum.UPDATE_NOT_EXISTS, not_exists);
		return resultMap;
	}
	/**
	 * 
	* @Title: getAllAuthorityByCompanyId 
	* @Description: 查询公司存在的所有权限数据 
	* @param @param companyId
	* @param @return    设定文件 
	* @return List<CompanyAuthority>    返回类型 
	* @author lijie
	* @throws
	 */
	private List<CompanyAuthority> getAllAuthorityByCompanyId(String companyId) {
		CompanyAuthority select = new CompanyAuthority();
		select.setCompanyId(companyId);
		List<CompanyAuthority> result = companyAuthorityMapper.select(select);
		return null == result ? new ArrayList<>() : result;
	}
	/**
	 * 
	* @Title: getMenuAuthority 
	* @Description: 封装值 
	* @param @param insert
	* @param @param vo    设定文件 
	* @return void    返回类型 
	* @author lijie
	* @throws
	 */
	private void getMenuAuthority(final CompanyAuthority insert, final PermissionInfoDTO dto) {
		insert.setBaseAuthorityId(dto.getAuthId());
		insert.setAppSeq(dto.getAppSeq());
		insert.setAscription(dto.getAscription());
		insert.setIcon(dto.getIcon());
		insert.setAuthorityName(dto.getAuthName());
		insert.setAuthoritySeq(dto.getAuthSeq());
		insert.setAuthorityUrl(dto.getAuthUrl());
		insert.setAppUrl(dto.getAppUrl());
		insert.setType(dto.getType());
		insert.setAuthCode(dto.getAuthCode());
		insert.setBaseMenuId(dto.getMenuId());
	}
	/**
	 * @throws CloneNotSupportedException 
	* @Title: packAddMenus 
	* @Description: 封装添加菜单/菜单权限的数据 返回数据格式为 LinkedList
	* @param @param list
	* @param @return    设定文件 
	* @return List<AddMenuDTO>    返回类型 
	* @author lijie
	* @throws
	 */
	public static List<AddMenuDTO> packAddMenus(List<MenuDTO> list, Map<String, List<Long>> menu_auth_map,
			String companyId) throws CloneNotSupportedException {
		final List<AddMenuDTO> result = new LinkedList<>();
		if (CollectionUtils.isNotEmpty(list)) {
			final List<AddMenuDTO> copys = new ArrayList<>(list.size());
			final AddMenuDTO dto = new AddMenuDTO();
			dto.setCreaterId(CommonConstants.SYSTEM_USER);
			for (MenuDTO vo : list) {
				BeanUtils.copyProperties(vo, dto);
				dto.setMenuId(vo.getId());
				dto.setAuths(menu_auth_map.get(vo.getId()));
				dto.setCompanyId(companyId);
				copys.add(dto.clone());
			}
			// 封装返回菜单数据
			if (!copys.isEmpty()) {
				for (AddMenuDTO amt : copys) {
					if (StringUtils.isBlank(amt.getParentId())) {
						result.add(next(amt, copys));
					}
				}
			}
		}
		return result;
	}
	/**
	 * 
	* @Title: next 
	* @Description: 获取下一级菜单
	* @param @param parent
	* @param @param copys
	* @param @return    设定文件 
	* @return AddMenuDTO    返回类型 
	* @author lijie
	* @throws
	 */
	private static AddMenuDTO next(final AddMenuDTO parent, final List<AddMenuDTO> copys) {
		List<AddMenuDTO> childs = new LinkedList<>();
		for (AddMenuDTO amt : copys) {
			if (parent.getMenuId().equals(amt.getParentId())) {
				childs.add(next(amt, copys));
			}
		}
		parent.setNexts(childs);
		return parent;
	}

	@Override
	public Result getCompanyInfoByCorUrl(String corUrl) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		if (StringUtils.isBlank(corUrl)) {
			return ResultUtil.setResult(result, RespCode.Code.REQUEST_DATA_ERROR, 
					"公司url不能为空");
		}
		try {
			CompanyInfoVO info = companyAuthorityMapper.selectCompanyInfo(corUrl);
			if (null != info) {
				return ResultUtil.setResult(result, RespCode.Code.SUCCESS, info);
			}
		} catch (Exception e) {
			log.error("根据公司url获取公司信息", e);
		}
		return result;
	}

	@Override
	public Result getPersonalPermissions(Long userId) {
		log.info("查询个人权限数据入参={}", userId);
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		if (null == userId) {
			return ResultUtil.setResult(result, RespCode.Code.REQUEST_DATA_ERROR, 
					"用户ID不能为空");
		}
		try {
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS,
					companyAuthorityMapper.selectPersonalPermissions(userId));
		} catch (Exception e) {
			log.error("查询个人权限数据异常", e);
		}
		return result;
	}

	@Override
	public Result getPositionPermissions(Long positionId) {
		log.info("查询职位权限数据入参={}", positionId);
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		if (null == positionId) {
			return ResultUtil.setResult(result, RespCode.Code.REQUEST_DATA_ERROR, 
					"职位ID不能为空");
		}
		try {
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS,
					companyAuthorityMapper.selectPositionPermissions(positionId));
		} catch (Exception e) {
			log.error("查询职位权限数据异常", e);
		}
		return result;
	}

	@Override
	public Result getPermissions(String companyId) {
		log.info("根据公司查询权限数据入参={}", companyId);
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		if (StringUtils.isNotBlank(companyId)) {
			return ResultUtil.setResult(result, RespCode.Code.REQUEST_DATA_ERROR, 
					"公司ID不能为空");
		}
		try {
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS,
					companyAuthorityMapper.selectPermissions(companyId));
		} catch (Exception e) {
			log.error("根据公司查询权限数据异常", e);
		}
		return result;
	}

	@Override
	@Transactional
	public Result addPersonalPermissions(Long userId, List<Long> authorityIds, Long[] menuIds) {
		log.info("新增个人权限数据入参={},authorityIds={}", userId, JSON.toJSONString(authorityIds));
		Result result = ResultUtil.getResult(RespCode.Code.REQUEST_DATA_ERROR);
		if (null == userId) {
			result.setMessage("用户ID不能为空");
			return result;
		}
		try {
			if (CollectionUtils.isNotEmpty(authorityIds)) {
				UserAuthority delete = new UserAuthority();
				delete.setUserId(userId);
				staffAuthorityMapper.delete(delete);
				final List<UserAuthority> recordList = new ArrayList<>(authorityIds.size());
				final UserAuthority insert = new UserAuthority();
				insert.setCreaterId(userId);
				insert.setCreateTime(new Date());
				insert.setUserId(userId);
				for (Long id : authorityIds) {
					insert.setAuthorityId(id);
					recordList.add(insert.clone());
				}
				staffAuthorityMapper.insertList(recordList);
			}
			if (null != menuIds && menuIds.length > 0) {
				// 菜单角色用户权限添加
				List<RoleVO> voList = companyRoleMapper.selectRoleListByUserId(userId);
				if (CollectionUtils.isEmpty(voList)) {
					result.setMessage("用户角色不能为空");
					return result;
				}
				companyRoleService.addRoleMenus(menuIds, voList.get(0).getId(), userId, new Date());
			}
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS);
		} catch (Exception e) {
			log.error("新增个人权限数据异常", e);
			throw new PermissionException(PermissionExceptionEnum.ADD_PERSONAL_PERMISSION);
		}
	}

	@Override
	public Result getAuthorizeData(PermissionQueryDTO dto) {
		log.info("查询授权数据入参={}", JSON.toJSONString(dto));
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		String errorStr = checkAuthorizeData(dto);
		if (StringUtils.isNotBlank(errorStr)) {
			return ResultUtil.setResult(result, RespCode.Code.REQUEST_DATA_ERROR, errorStr);
		}
		try {
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS, menuPermissionFactory.genMenuAuth(dto));
		} catch (Exception e) {
			log.error("查询授权数据异常", e);
		}
		return result;
	}
	/**
	 * 
	* @Title: checkAuthorizeData 
	* @Description: 校验授权请求数据 
	* @param @param dto
	* @param @return    设定文件 
	* @return String    返回类型 
	* @author lijie
	* @throws
	 */
	private String checkAuthorizeData(PermissionQueryDTO dto) {
		String errorStr = dto.validateForm();
		if (StringUtils.isNotBlank(errorStr)) {
			return errorStr;
		}
		if (dto.getOperType() == 2) {
			if (null == dto.getUserId()) {
				return "用户ID不能为空";
			}
		}
		return null;
	}
}
