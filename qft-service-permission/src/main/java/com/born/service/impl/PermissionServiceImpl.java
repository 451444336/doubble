package com.born.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.born.core.base.BaseModel;
import com.born.core.base.BaseService;
import com.born.core.base.DataBaseParameters;
import com.born.core.constant.DataBaseEnum;
import com.born.entity.Authority;
import com.born.entity.CompanyAuthority;
import com.born.entity.Menu;
import com.born.entity.MenuAuthority;
import com.born.entity.UserAuthority;
import com.born.facade.constant.MenuAuthEnum;
import com.born.facade.dto.permission.ChangeAuthDTO;
import com.born.facade.dto.permission.PermissionQueryDTO;
import com.born.facade.exception.PermissionException;
import com.born.facade.exception.PermissionExceptionEnum;
import com.born.facade.service.ICompanyRoleService;
import com.born.facade.service.IPermissionService;
import com.born.facade.vo.RoleVO;
import com.born.facade.vo.company.CompanyInfoVO;
import com.born.mapper.AuthorityMapper;
import com.born.mapper.CompanyAuthorityMapper;
import com.born.mapper.CompanyRoleMapper;
import com.born.mapper.MenuAuthorityMapper;
import com.born.mapper.MenuMapper;
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
public class PermissionServiceImpl extends BaseService<BaseModel, Authority> implements IPermissionService {

	@Autowired
	private CompanyAuthorityMapper companyAuthorityMapper;
	
	@Autowired
	private UserAuthorityMapper staffAuthorityMapper;
	
	@Autowired
	private MenuAuthorityMapper menuAuthorityMapper;
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private AuthorityMapper authorityMapper;
	
	@Autowired
	private MenuPermissionFactory menuPermissionFactory;
	
	@Autowired
	private CompanyRoleMapper companyRoleMapper;
	
	@Autowired
	private ICompanyRoleService companyRoleService;
	

	@Override
	public Result getCompanyInfoByCorUrl(String corUrl) {
		if (StringUtils.isBlank(corUrl)) {
			return ResultUtil.requestDataError("公司url不能为空");
		}
		try {
			CompanyInfoVO info = companyAuthorityMapper.selectCompanyInfo(corUrl);
			if (null != info) {
				return ResultUtil.success(info);
			}
		} catch (Exception e) {
			log.error("根据公司url获取公司信息", e);
		}
		return ResultUtil.fail();
	}

	@Override
	public Result getPersonalPermissions(Long userId) {
		log.info("查询个人权限数据入参={}", userId);
		if (null == userId) {
			return ResultUtil.requestDataError("用户ID不能为空");
		}
		try {
			return ResultUtil.success(companyAuthorityMapper.selectPersonalPermissions(userId));
		} catch (Exception e) {
			log.error("查询个人权限数据异常", e);
			return ResultUtil.fail();
		}
	}

	@Override
	public Result getPositionPermissions(Long positionId) {
		log.info("查询职位权限数据入参={}", positionId);
		if (null == positionId) {
			return ResultUtil.requestDataError("职位ID不能为空");
		}
		try {
			return ResultUtil.success(companyAuthorityMapper.selectPositionPermissions(positionId));
		} catch (Exception e) {
			log.error("查询职位权限数据异常", e);
			return ResultUtil.fail();
		}
	}

	@Override
	public Result getPermissions(Long companyId) {
		log.info("根据公司查询权限数据入参={}", companyId);
		if (null == companyId) {
			return ResultUtil.requestDataError("公司ID不能为空");
		}
		try {
			return ResultUtil.success(companyAuthorityMapper.selectPermissions(companyId));
		} catch (Exception e) {
			log.error("根据公司查询权限数据异常", e);
			return ResultUtil.fail();
		}
	}

	@Override
	@Transactional
	public Result addPersonalPermissions(Long userId, List<Long> authorityIds, Long[] menuIds) {
		log.info("新增个人权限数据入参={},authorityIds={}", userId, JSON.toJSONString(authorityIds));
		if (null == userId) {
			return ResultUtil.requestDataError("用户ID不能为空");
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
					return ResultUtil.requestDataError("用户角色不能为空");
				}
				companyRoleService.addRoleMenus(menuIds, voList.get(0).getId(), userId, new Date());
			}
			return ResultUtil.success();
		} catch (Exception e) {
			log.error("新增个人权限数据异常", e);
			throw new PermissionException(PermissionExceptionEnum.ADD_PERSONAL_PERMISSION);
		}
	}

	@Override
	public Result getAuthorizeData(PermissionQueryDTO dto) {
		log.info("查询授权数据入参={}", JSON.toJSONString(dto));
		String errorStr = checkAuthorizeData(dto);
		if (StringUtils.isNotBlank(errorStr)) {
			return ResultUtil.requestDataError(errorStr);
		}
		try {
			return ResultUtil.success(menuPermissionFactory.genMenuAuth(dto));
		} catch (Exception e) {
			log.error("查询授权数据异常", e);
			return ResultUtil.fail();
		}
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
	
	@Override
	@Transactional
	public Result addAuthority(ChangeAuthDTO dto) {
		log.info("添加权限数据入参={}", JSON.toJSONString(dto));
		String errorStr = dto.validateForm();
		if (StringUtils.isNotBlank(errorStr)) {
			return ResultUtil.fail(RespCode.Code.REQUEST_DATA_ERROR, errorStr);
		}
		try {
			if (checkAuthCode(dto.getAuthCode(), dto.getAuthId())) {
				return ResultUtil.fail(PermissionExceptionEnum.CODE_EXISTS);
			}
			Authority record = new Authority();
			BeanUtils.copyProperties(dto, record);
			if (null == dto.getAuthId()) {
				Menu select = new Menu();
				select.setId(dto.getMenuId());
				select.setIsDelete(DataBaseEnum.NOT_DELETE.getStatus());
				select = menuMapper.selectOne(select);
				if (null == select) {
					return ResultUtil.fail(PermissionExceptionEnum.MENUS_NOT_EXISTS);
				}
				record.setCreaterId(dto.getOperId());
				record.setCreateTime(new Date());
				record.setIsDelete(DataBaseEnum.NOT_DELETE.getStatus());
				record.setIsUsable(MenuAuthEnum.AVAILABLE.getStatus());
				authorityMapper.insertUseGeneratedKeys(record);
				// 添加菜单权限关系
				MenuAuthority mInsert = new MenuAuthority();
				mInsert.setAuthorityId(record.getId());
				mInsert.setCreaterId(dto.getOperId());
				mInsert.setCreateTime(new Date());
				mInsert.setMenuId(dto.getMenuId());
				menuAuthorityMapper.insert(mInsert);

				// 新增公司权限数据
				CompanyAuthority ca = new CompanyAuthority();
				ca.setAuthorityId(record.getId());
				ca.setCompanyId(dto.getCompanyId());
				ca.setCreaterId(dto.getOperId());
				ca.setCreateTime(new Date());
				ca.setIsDelete(DataBaseEnum.NOT_DELETE.getStatus());
				companyAuthorityMapper.insert(ca);
			} else {
				record.setUpdaterId(dto.getOperId());
				record.setUpdateTime(new Date());
				record.setId(dto.getAuthId());
				authorityMapper.updateByPrimaryKeySelective(record);
			}
			return ResultUtil.success();
		} catch (Exception e) {
			log.error("添加权限数据异常", e);
			throw new PermissionException(PermissionExceptionEnum.ADD_PERMISSION_ERROR);
		}
	}

	private boolean checkAuthCode(String authCode, Long authId) {
		Authority record = new Authority();
		record.setAuthCode(authCode);
		record = authorityMapper.selectOne(record);
		return null != record && !record.getId().equals(authId);
	}
	
	@Override
	protected DataBaseParameters<Authority> getDataBaseParameters() {
		DataBaseParameters<Authority> result = new DataBaseParameters<>();
		result.setEntityClass(Authority.class);
		result.setMapper(authorityMapper);
		return result;
	}
}
