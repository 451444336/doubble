package com.born.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.born.core.constant.CommonConstants;
import com.born.core.page.PageBean;
import com.born.facade.constant.AuthChangeEnum;
import com.born.facade.constant.MenuAuthEnum;
import com.born.facade.dto.menu.AddMenuDTO;
import com.born.facade.dto.permission.AddPermissionDTO;
import com.born.facade.dto.permission.PermissionInfoDTO;
import com.born.facade.dto.permission.PermissionQueryDTO;
import com.born.facade.entity.AuthorityChange;
import com.born.facade.entity.CompanyAuthority;
import com.born.facade.entity.MenuAuthorityBase;
import com.born.facade.entity.UserAuthority;
import com.born.facade.exception.PermissionException;
import com.born.facade.exception.PermissionExceptionEnum;
import com.born.facade.service.IMenuService;
import com.born.facade.service.IPermissionService;
import com.born.facade.vo.MenuAuthorityVO;
import com.born.facade.vo.company.CompanyInfoVO;
import com.born.facade.vo.permission.MenuPermissionVO;
import com.born.facade.vo.permission.PermissionVO;
import com.born.mapper.AuthorityChangeMapper;
import com.born.mapper.CompanyAuthorityMapper;
import com.born.mapper.MenuAuthorityBaseMapper;
import com.born.mapper.MenuAuthorityMapper;
import com.born.mapper.UserAuthorityMapper;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
	private MenuAuthorityBaseMapper menuAuthorityBaseMapper;
	
	@Autowired
	private AuthorityChangeMapper authorityChangeMapper;
	
	@Autowired
	private UserAuthorityMapper staffAuthorityMapper;
	
	@Autowired
	private MenuAuthorityMapper menuAuthorityMapper;
	
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
			if (CollectionUtils.isNotEmpty(dto.getMenus())) {
				// 封装权限菜单基础数据并添加权限数据
				Map<AuthOperEnum, Object> map = initMenuAuthorityBase(dto.getMenus(), dto.getTemplateId(), dto.getCompanyId());
				Map<String, Long> add_company_authority = (Map<String, Long>) map
						.get(AuthOperEnum.ADD_COMPANY_AUTHORITY);
				Map<String, Long> all_map = (Map<String, Long>) map.get(AuthOperEnum.ALL_MAP);
				List<MenuAuthorityBase> addBases = (List<MenuAuthorityBase>) map.get(AuthOperEnum.ADD_AUTH);
				if (insertMenuAuthorityBase(addBases)) {
					for (MenuAuthorityBase mab : addBases) {
						all_map.put(mab.getAuthorityId(), mab.getId());
						if (add_company_authority.containsKey(mab.getAuthorityId())) {
							add_company_authority.put(mab.getAuthorityId(), mab.getId());
						}
					}
				}
				// 修改数据状态（存在/不存在的权限数据）
				updateMenuAuthorityBase(map);
				// 新增公司权限数据
				Map<Long, Long> auths = insertCompanyAuthority(collToList(add_company_authority.values()),
						dto.getCompanyId());
				// 新增权限变更记录表数据
				insertAuthorityChange(dto);
				// 封装菜单数据并添加菜单
				Result addMenuResult = menuService.addMenu(packAddMenus(dto.getMenus(), all_map, auths));
				log.info("添加菜单返回数据={}", JSON.toJSONString(addMenuResult));
				if (!addMenuResult.isSuccess()) {
					throw new PermissionException(PermissionExceptionEnum.ADD_MENU_ERROR);
				}
				return ResultUtil.setResult(result, RespCode.Code.SUCCESS);
			} else {
				result.setMessage("未查询到公司菜单/权限数据");
				log.warn("未查询到公司菜单/权限数据");
			}
		} catch (Exception e) {
			log.error("权限添加异常", e);
			throw new PermissionException(PermissionExceptionEnum.ADD_PERMISSION_ERROR);
		}
		return result;
	}
	
	private List<Long> collToList(Collection<Long> colls) {
		final List<Long> result = new ArrayList<>(colls.size());
		if (CollectionUtils.isNotEmpty(colls)) {
			result.addAll(colls);
		}
		return result;
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
		final AuthorityChange change = new AuthorityChange();
		change.setCompanyId(dto.getCompanyId());
		change.setCreaterId(CommonConstants.SYSTEM_USER);
		change.setCreateTime(new Date());
		change.setOldUserId(dto.getUserId());
		change.setOperType(AuthChangeEnum.ADD.getStatus());
		change.setTemplateId(dto.getTemplateId());
		final List<PermissionInfoDTO> menus = dto.getMenus();
		final List<AuthorityChange> recordList = new ArrayList<>(menus.size());
		for (PermissionInfoDTO auth : menus) {
			change.setAuthorityId(auth.getId());
			recordList.add(change.clone());
		}
		authorityChangeMapper.insertList(recordList);
	}
	/**
	 * @throws CloneNotSupportedException 
	* @Title: changeCompanyAuthority 
	* @Description: 变更公司权限 
	* @param @param inserts
	* @param @param companyId    设定文件 
	* @return void    返回类型 
	* @author lijie
	* @throws
	 */
	private Map<Long, Long> insertCompanyAuthority(List<Long> inserts, String companyId)
			throws CloneNotSupportedException {
		final Map<Long, Long> result = new HashMap<>();
		List<CompanyAuthority> list = companyAuthorityMapper.selectAll();
		if (CollectionUtils.isNotEmpty(list)) {
			for (CompanyAuthority ca : list) {
				result.put(ca.getAuthorityBaseId(), ca.getId());
			}
		}
		if (CollectionUtils.isNotEmpty(inserts)) {
			final CompanyAuthority cah = new CompanyAuthority();
			cah.setCompanyId(companyId);
			cah.setCreateTime(new Date());
			cah.setCreaterId(CommonConstants.SYSTEM_USER);
			List<CompanyAuthority> recordList = new ArrayList<>(inserts.size());
			for (Long id : inserts) {
				cah.setAuthorityBaseId(id);
				recordList.add(cah.clone());
			}
			companyAuthorityMapper.insertList(recordList);
			for (CompanyAuthority ca : recordList) {
				result.put(ca.getAuthorityBaseId(), ca.getId());
			}
		}
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
			menuAuthorityBaseMapper.updateMenuAuthorityByAuthIds(not_exists, MenuAuthEnum.DELETE.getStatus(),
					CommonConstants.SYSTEM_USER);
		}

		List<Long> exists = (List<Long>) map.get(AuthOperEnum.UPDATE_EXISTS);
		if (CollectionUtils.isNotEmpty(exists)) {
			// 修改新增范围之内菜单为正常状态
			menuAuthorityBaseMapper.updateMenuAuthorityByAuthIds(exists, MenuAuthEnum.NOT_DELETE.getStatus(),
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
	private boolean insertMenuAuthorityBase(List<MenuAuthorityBase> addBases){
		if (CollectionUtils.isNotEmpty(addBases)) {
			menuAuthorityBaseMapper.insertList(addBases);
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
		UPDATE_EXISTS,
		/**
		 * 所有权限对应的自增ID标记
		 */
		ALL_MAP,
		/**
		 * 新增权限标记
		 */
		ADD_COMPANY_AUTHORITY
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
	private Map<AuthOperEnum, Object> initMenuAuthorityBase(List<PermissionInfoDTO> menus, String templateId,
			String companyId) throws CloneNotSupportedException {
		final Map<AuthOperEnum, Object> resultMap = new HashMap<>();
		// 查询已存在的权限数据
		MenuAuthorityBase select = new MenuAuthorityBase();
		select.setCompanyId(companyId);
		final List<MenuAuthorityBase> alllist = menuAuthorityBaseMapper.select(select);
		Map<String, MenuAuthorityBase> existsMap = new HashMap<String, MenuAuthorityBase>();
		if (CollectionUtils.isNotEmpty(alllist)) {
			for (MenuAuthorityBase base : alllist) {
				existsMap.put(base.getAuthorityId(), base);
			}
		}
		// 需要添加的数据
		final List<MenuAuthorityBase> adds = new ArrayList<>(menus.size());
		// 变更后存在的数据/需要修改删除状态的数据
		final List<Long> exists = new ArrayList<>();
		// 变更后不存在的数据/需要修改为删除状态的数据
		final List<Long> not_exists = new ArrayList<>();
		// 所有的权限数据对应的base ID数据
		final Map<String, Long> all_map = new HashMap<>();
		
		final Map<String, Long> add_company_authority = new HashMap<>();
		MenuAuthorityBase insert = new MenuAuthorityBase();
		insert.setIsDelete(MenuAuthEnum.NOT_DELETE.getStatus());
		insert.setTemplateId(templateId);
		insert.setIsUsable(MenuAuthEnum.AVAILABLE.getStatus());
		// 存在则忽略，不存在则添加
		MenuAuthorityBase base;
		for (PermissionInfoDTO dto : menus) {
			base = existsMap.get(dto.getId());
			if (null == base) {
				if (CommonConstants.AUTH_FLAG.equals(dto.getMenuLevel())) {
					add_company_authority.put(dto.getId(), null);
				}
				getMenuAuthorityBase(insert, dto);
				adds.add(insert.clone());
			} else {
				all_map.put(base.getAuthorityId(), base.getId());
				if (MenuAuthEnum.DELETE.getStatus().equals(base.getIsDelete())) {
					exists.add(base.getId());
				}
				existsMap.remove(dto.getId());
			}
		}
		Collection<MenuAuthorityBase> not_exists_base = existsMap.values();
		if (not_exists_base.size() > 0) {
			for (MenuAuthorityBase neb : not_exists_base) {
				not_exists.add(neb.getId());
			}
		}
		resultMap.put(AuthOperEnum.ADD_AUTH, adds);
		resultMap.put(AuthOperEnum.UPDATE_EXISTS, exists);
		resultMap.put(AuthOperEnum.UPDATE_NOT_EXISTS, not_exists);
		resultMap.put(AuthOperEnum.ALL_MAP, all_map);
		resultMap.put(AuthOperEnum.ADD_COMPANY_AUTHORITY, add_company_authority);
		return resultMap;
	}
	/**
	 * 
	* @Title: getMenuAuthorityBase 
	* @Description: 封装值 
	* @param @param insert
	* @param @param vo    设定文件 
	* @return void    返回类型 
	* @author lijie
	* @throws
	 */
	private void getMenuAuthorityBase(MenuAuthorityBase insert, PermissionInfoDTO dto) {
		insert.setAuthorityId(dto.getId());
		insert.setAppSeq(dto.getAppSeq());
		insert.setAscription(dto.getAscription());
		insert.setIcon(dto.getIcon());
		insert.setAuthorityName(dto.getMenuName());
		insert.setAuthoritySeq(dto.getMenuSeq());
		insert.setAuthorityUrl(dto.getMenuUrl());
		insert.setAppUrl(dto.getAppUrl());
		insert.setCompanyId(dto.getCompanyId());
		insert.setType(dto.getType());
		insert.setOperType(dto.getOperType());
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
	public static List<AddMenuDTO> packAddMenus(List<PermissionInfoDTO> list, Map<String, Long> allMap,
			Map<Long, Long> auths) throws CloneNotSupportedException {
		final List<AddMenuDTO> result = new LinkedList<>();
		final List<AddMenuDTO> copys = new ArrayList<>(list.size());
		final AddMenuDTO dto = new AddMenuDTO();
		dto.setCreaterId(CommonConstants.SYSTEM_USER);
		if (CollectionUtils.isNotEmpty(list)) {
			for (PermissionInfoDTO vo : list) {
				dto.setCompanyId(vo.getCompanyId());
				dto.setMenuLevel(vo.getMenuLevel());
				dto.setMenuId(vo.getId());
				dto.setParentId(vo.getParentId());
				dto.setAuthorityBaseId(allMap.get(vo.getId()));
				copys.add(dto.clone());
			}
		}
		// 封装返回菜单数据
		if (!copys.isEmpty()) {
			for (AddMenuDTO amt : copys) {
				if (StringUtils.isBlank(amt.getParentId())) {
					result.add(next(amt, copys));
				}
			}
			// 封装菜单对应的权限ID数据
			for (AddMenuDTO amt : result) {
				amt.setAuths(getAuths(amt.getNexts().iterator(), auths));
			}
		}
		return result;
	}
	
	/**
	 * 
	* @Title: getAuths 
	* @Description: 封装菜单对应的权限数据
	* @param @param list
	* @param @param auths
	* @param @return    设定文件 
	* @return List<Long>    返回类型 
	* @author lijie
	* @throws
	 */
	private static List<Long> getAuths(final Iterator<AddMenuDTO> it, final Map<Long, Long> auths) {
		final List<Long> result = new ArrayList<Long>();
		Long authId;
		AddMenuDTO amt;
		while (it.hasNext()) {
			amt = it.next();
			authId = auths.get(amt.getAuthorityBaseId());
			// 如果是权限数据则从菜单中移除掉
			if (null != authId) {
				result.add(authId);
				it.remove();
			}
			if (CollectionUtils.isNotEmpty(amt.getNexts())) {
				amt.setAuths(getAuths(amt.getNexts().iterator(), auths));
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
	@Transactional
	public Result updatePermission(PermissionInfoDTO dto) {
		log.info("权限编辑入参={}", JSON.toJSONString(dto));
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		String errorStr = dto.validateForm();
		if (StringUtils.isNotBlank(errorStr)) {
			return ResultUtil.setResult(result, RespCode.Code.REQUEST_DATA_ERROR, errorStr);
		}
		try {
			MenuAuthorityBase selectOne = new MenuAuthorityBase();
			selectOne.setAuthorityId(dto.getId());
			selectOne.setCompanyId(dto.getCompanyId());
			selectOne = menuAuthorityBaseMapper.selectOne(selectOne);
			if (null == selectOne) {
				return ResultUtil.setResult(result, PermissionExceptionEnum.PERMISSION_NOT_EXISTS);
			}

			MenuAuthorityBase update = selectOne.clone();
			update.setAppSeq(dto.getAppSeq());
			update.setAppUrl(dto.getAppUrl());
			update.setAscription(dto.getAscription());
			update.setAuthorityName(dto.getMenuName());
			update.setAuthoritySeq(dto.getMenuSeq());
			update.setAuthorityUrl(dto.getMenuUrl());
			update.setIcon(dto.getIcon());
			update.setIsUsable(dto.getIsUsable());
			update.setOperType(dto.getOperType());
			update.setUpdateTime(new Date());
			update.setUpdaterId(CommonConstants.SYSTEM_USER);
			menuAuthorityBaseMapper.updateByPrimaryKeySelective(update);

			return ResultUtil.setResult(result, RespCode.Code.SUCCESS);
		} catch (Exception e) {
			log.error("权限编辑异常", e);
			throw new PermissionException(PermissionExceptionEnum.UPDATE_PERMISSION_ERROR);
		}
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
	public Result getCompanyInfosByPage(PageBean pageBean) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		try {
			PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
			List<MenuAuthorityBase> list = menuAuthorityBaseMapper.selectAll();
			PageInfo<MenuAuthorityBase> pageInfo = new PageInfo<>(list);
			result.setData(pageInfo.getList());
			result.setCount(pageInfo.getTotal());
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS);
		} catch (Exception e) {
			log.error("查询分页数据异常", e);
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
	public Result addPersonalPermissions(Long userId, List<Long> authorityIds) {
		log.info("新增个人权限数据入参={},authorityIds={}", userId, JSON.toJSONString(authorityIds));
		Result result = ResultUtil.getResult(RespCode.Code.REQUEST_DATA_ERROR);
		if (CollectionUtils.isEmpty(authorityIds)) {
			result.setMessage("权限ID不能为空");
			return result;
		}
		if (null == userId) {
			result.setMessage("用户ID不能为空");
			return result;
		}
		try {
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
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS);
		} catch (Exception e) {
			log.error("新增个人权限数据异常", e);
			throw new PermissionException(PermissionExceptionEnum.ADD_PERSONAL_PERMISSION);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Result getAuthorizeData(PermissionQueryDTO dto) {
		log.info("查询授权数据入参={}", JSON.toJSONString(dto));
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		String errorStr = checkAuthorizeData(dto);
		if (StringUtils.isNotBlank(errorStr)) {
			return ResultUtil.setResult(result, RespCode.Code.REQUEST_DATA_ERROR, errorStr);
		}
		try {
			result = getMenuPermissions(dto);
			if (!result.isSuccess()) {
				return result;
			}
			// 根据菜单id 查询菜单操作权限数据
			List<PermissionVO> mps = result.getData(List.class);
			// 封装返回分组对象
			final Map<Long, List<PermissionVO>> resultMap = new HashMap<>();
			List<PermissionVO> list;
			for (PermissionVO pv : mps) {
				list = resultMap.get(pv.getMenuId());
				if (null == list) {
					list = new LinkedList<>();
					resultMap.put(pv.getMenuId(), list);
				}
				list.add(pv);
			}
			if (resultMap.size() > 0) {
				List<MenuPermissionVO> rList = new ArrayList<>(resultMap.size());
				MenuPermissionVO mpv;
				for (Map.Entry<Long, List<PermissionVO>> me : resultMap.entrySet()) {
					mpv = new MenuPermissionVO();
					mpv.setMenuId(me.getKey());
					mpv.setMenuName(me.getValue().get(0).getMenuName());
					mpv.setPermissions(me.getValue());
					rList.add(mpv);
				}
				return ResultUtil.setResult(result, RespCode.Code.SUCCESS, rList);
			}
		} catch (Exception e) {
			log.error("查询授权数据异常", e);
		}
		return result;
	}

	private Result getMenuPermissions(PermissionQueryDTO dto) throws CloneNotSupportedException {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		// 根据菜单id 查询菜单操作权限数据
		List<PermissionVO> mps = getPermissionsByMenuIds(dto.getMenuIds());
		if (CollectionUtils.isEmpty(mps)) {
			result.setMessage("根据菜单ID:" + JSON.toJSONString(dto.getMenuIds()) + "未查询到相关数据");
			return result;
		}
		// 查询职位权限授权数据
		List<PermissionVO> positions = companyAuthorityMapper.selectPositionPermissions(dto.getPositionId());
		// 职位权限数据ID
		final Set<Long> positionSets = new HashSet<>();
		if (CollectionUtils.isNotEmpty(positions)) {
			for (PermissionVO pv : positions) {
				positionSets.add(pv.getAuthorityId());
			}
		}
		// 查询过滤职位权限
		if (MenuAuthEnum.POSITION_AUTH.getStatus().equals(dto.getOperType())) {
			for (PermissionVO mpv : mps) {
				if (positionSets.contains(mpv.getAuthorityId())) {
					mpv.setIsCheck(MenuAuthEnum.CHECK.getStatus());
				}
			}
			// 查询过滤个人权限
		} else if (MenuAuthEnum.PERSION_AUTH.getStatus().equals(dto.getOperType())) {
			if (CollectionUtils.isEmpty(positions)) {
				result.setMessage("当前职位没得权限数据");
				return result;
			}
			Iterator<PermissionVO> it = mps.iterator();
			while (it.hasNext()) {
				if (!positionSets.contains(it.next().getAuthorityId())) {
					it.remove();
				}
			}
			// 查询个人权限数据
			List<PermissionVO> users = companyAuthorityMapper.selectPersonalPermissions(dto.getUserId());
			if (CollectionUtils.isNotEmpty(users)) {
				final Set<Long> userSets = new HashSet<>();
				for (PermissionVO pv : users) {
					userSets.add(pv.getAuthorityId());
				}
				for (PermissionVO mpv : mps) {
					if (userSets.contains(mpv.getAuthorityId())) {
						mpv.setIsCheck(MenuAuthEnum.CHECK.getStatus());
					}
				}
			}
		}
		return ResultUtil.setResult(result, RespCode.Code.SUCCESS, mps);
	}
	/**
	 * @throws CloneNotSupportedException 
	* @Title: getPermissionsByMenuId 
	* @Description: 根据菜单ID查询权限数据 
	* @param @param menuId
	* @param @return    设定文件 
	* @return List<PermissionVO>    返回类型 
	* @author lijie
	* @throws
	 */
	private List<PermissionVO> getPermissionsByMenuIds(final List<Long> menuIds) throws CloneNotSupportedException {
		final List<PermissionVO> result = new LinkedList<>();
		List<MenuAuthorityVO> mAuths = menuAuthorityMapper.selectAuthorityListByMenuIds(menuIds);
		if (CollectionUtils.isNotEmpty(mAuths)) {
			final PermissionVO rt = new PermissionVO();
			for (MenuAuthorityVO m : mAuths) {
				rt.setMenuId(m.getMenuId());
				rt.setMenuName(m.getMenuName());
				rt.setAuthorityId(m.getAuthorityId());
				rt.setAuthorityName(m.getAuthorityName());
				rt.setAuthorityUrl(m.getAuthorityUrl());
				rt.setOperType(m.getOperType());
				result.add(rt.clone());
			}
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
