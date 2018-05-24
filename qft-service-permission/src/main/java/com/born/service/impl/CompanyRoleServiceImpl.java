package com.born.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.facade.dto.CompanyRoleDTO;
import com.born.facade.dto.OperateLogAuthorityDTO;
import com.born.facade.entity.CompanyRole;
import com.born.facade.entity.RoleRelation;
import com.born.facade.exception.PermissionException;
import com.born.facade.exception.PermissionExceptionEnum;
import com.born.facade.service.ICompanyRoleService;
import com.born.facade.vo.CompanyRoleVO;
import com.born.mapper.CompanyRoleMapper;
import com.born.mapper.OperateLogAuthorityMapper;
import com.born.mapper.RoleRelationMappper;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 角色管理
 * @author wangxy
 * @date 2018年4月27日 下午3:04:25
 */
@Slf4j
@Service(version = "1.0.0")
public class CompanyRoleServiceImpl implements ICompanyRoleService {

	@Autowired
	private CompanyRoleMapper companyRoleMapper;
	
	@Autowired
	private OperateLogAuthorityMapper operateLogAuthorityMapper;
	
	@Autowired
	private RoleRelationMappper roleRelationMappper;
	
	@Override
	@Transactional
	public Result add(CompanyRoleDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		// 验证参数
		if (StringUtils.isBlank(dto.getRoleName()) || dto.getIsAuthEdit() == null) {
			
			return ResultUtil.getResult(RespCode.Code.REQUEST_DATA_ERROR);
		}
		try {
			CompanyRole role = new CompanyRole();
			BeanUtils.copyProperties(dto, role);
			// 设置默认值
			role.setCreateTime(new Date());
			role.setIsDelete((byte)0);
			role.setIsValid((byte)1);
			// 保存角色数据
			companyRoleMapper.insertUseGeneratedKeys(role);
			// 保存角色关系数据
			RoleRelation record = new RoleRelation();
			record.setCompanyId(dto.getCompanyId());
			record.setCreaterId(dto.getCreaterId());
			record.setCreateTime(new Date());
			record.setRoleId(role.getId());
			ResultUtil.setResult(result,RespCode.Code.SUCCESS,roleRelationMappper.insert(record));
		} catch (Exception e) {
			log.error("添加角色失败（CompanyRoleServiceImpl.insert）..................."+e);
			throw new PermissionException(PermissionExceptionEnum.ADD_ROLE_ERROE);
		}
		return result;
	}

	@Override
	@Transactional
	public Result deleteById(Long id,OperateLogAuthorityDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		try {
			log.info("执行删除操作...");
			companyRoleMapper.deleteRoleByRoleId(id);
			// 删除角色菜单关联表
			companyRoleMapper.deleteRoleMenuByRoleId(id);
			//操作日志记录
			operateLogAuthorityMapper.insertOperateLogAuthority(dto);
			log.info("删除操作成功...");
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS);
		} catch (Exception e) {
			log.error("删除角色失败（CompanyRoleServiceImpl.deleteById）..................."+e);
			throw new PermissionException(PermissionExceptionEnum.DELETE_ROLE_ERROE);
		}
	}

	@Override
	@Transactional
	public Result update(CompanyRoleDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		// 验证参数
		if (dto.getId() == null) {
			return ResultUtil.getResult(RespCode.Code.REQUEST_DATA_ERROR, dto);
		}
		try {
			// 查询角色信息
			CompanyRole role = companyRoleMapper.selectByPrimaryKey(dto.getId());
			if(role == null) {
				return ResultUtil.getResult(RespCode.Code.NOT_QUERY_DATA);
			}
			BeanUtils.copyProperties(dto, role);
			// 保存数据
			log.info("执行修改操作...");
			ResultUtil.setResult(result,RespCode.Code.SUCCESS, companyRoleMapper.updateByPrimaryKeySelective(role));
			log.info("修改操作成功...");
		} catch (Exception e) {
			log.error("修改角色失败（CompanyRoleServiceImpl.update）..................."+e);
			throw new PermissionException(PermissionExceptionEnum.UPDATE_ROLE_ERROE);
		}
		return result;
	}

	@Override
	public CompanyRole queryById(Long id) {
		CompanyRole role = companyRoleMapper.selectByPrimaryKey(id);
		return role;
	}

	@Override
	@Transactional
	public Result addRoleMenus(Long[] menuIds, Long roleId, Long createrId, Date createTime) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		// 验证参数
		if (roleId == null || createrId == null || createTime == null) {
			return ResultUtil.getResult(RespCode.Code.REQUEST_DATA_ERROR);
		}
		try {
			// 清空原有菜单
			companyRoleMapper.deleteRoleMenuByRoleId(roleId);
			if(menuIds[0] == null){
				return ResultUtil.getResult(RespCode.Code.SUCCESS);
			}
			// 绑定新菜单
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("menuIds", menuIds);
			map.put("roleId", roleId);
			map.put("createTime", createTime);
			map.put("createrId", createrId);
			companyRoleMapper.insertRoleMenuBatch(map);
			ResultUtil.setResult(result,RespCode.Code.SUCCESS);
		} catch (Exception e) {
			log.error("角色绑定菜单失败（CompanyRoleServiceImpl.bindRoleMenu）..................."+e);
			throw new PermissionException(PermissionExceptionEnum.BIND_ROLE_MENU_ERROE);
		}
		return result;
	}

	@Override
	public Result getRoleByUserId(Long id) {
		log.info("根据用户Id查询角色集合入参, userId ={}", id);
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		if (null == id) {
			result.setMessage("用户ID不能为空");
			return result;
		}
		try {
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS, companyRoleMapper.selectRoleListByUserId(id));
		} catch (Exception e) {
			log.error("根据用户Id查询角色集合异常（CompanyRoleServiceImpl.getRoleByUserId）..................."+e);
		}
		return result;
	}

	@Override
	public Result getRoleList(CompanyRoleDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		try {
		 	PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		 	
	        List<CompanyRoleVO> list = companyRoleMapper.selectCompanyRoleList(dto);
	        PageInfo<CompanyRoleVO> pageInfo = new PageInfo<>(list);
	        result.setData(pageInfo.getList());
			result.setCount(pageInfo.getTotal());
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS);
		} catch (Exception e) {
			log.error("查询角色分页数据失败（CompanyRoleServiceImpl.getRoleByUserId）..................."+e);
		}
        return result;
	}

	public Result getRoleMenus(Long roleId){
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		if(roleId == null){
			return ResultUtil.getResult(RespCode.Code.REQUEST_DATA_ERROR);
		}
		try{
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS, companyRoleMapper.selectRoleMenus(roleId));
		}catch(Exception e){
			log.error("根据角色ID查询角色菜单数据失败（CompanyRoleServiceImpl.getRoleMenus）..................."+e);
		}
		return result;
	}
}
