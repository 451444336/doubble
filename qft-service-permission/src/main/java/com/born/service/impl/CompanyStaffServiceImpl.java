package com.born.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.facade.dto.CompanyStaffDTO;
import com.born.facade.dto.staff.FindStaffListDTO;
import com.born.facade.dto.staff.PositionStaffDTO;
import com.born.facade.dto.user.DeteleUserDTO;
import com.born.facade.dto.user.UserRoleDTO;
import com.born.facade.entity.CompanyRole;
import com.born.facade.entity.CompanyStaff;
import com.born.facade.entity.User;
import com.born.facade.exception.PermissionException;
import com.born.facade.exception.PermissionExceptionEnum;
import com.born.facade.service.ICompanyStaffService;
import com.born.facade.vo.CompanyStaffVO;
import com.born.facade.vo.RoleVO;
import com.born.mapper.CompanyRoleMapper;
import com.born.mapper.CompanyStaffMapper;
import com.born.mapper.SysUserMapper;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 员工信息实现类
 * 
 * @author zys
 *
 */
@Slf4j
@Service(version = "1.0.0")
public class CompanyStaffServiceImpl implements ICompanyStaffService {
	@Autowired
	private CompanyStaffMapper staffMapper;
	
	@Autowired
	private SysUserMapper userMapper;
	
	@Autowired
	private CompanyRoleMapper roleMapper;

	@Override
	public Result getCompanyStaff(String userId) {
		log.info("get company staff info for userId={}", userId);
		if (userId == null) {
			return ResultUtil.getResult(RespCode.Code.FAIL, "参数不能为空");
		}
		try {
			CompanyStaff companyStaff = staffMapper.selectStaffByUserId(userId);
			return ResultUtil.getResult(RespCode.Code.SUCCESS, companyStaff);
		} catch (Exception e) {
			log.error("查询用户基本信息异常", e);
			return ResultUtil.getResult(RespCode.Code.FAIL, "查询用户基本信息异常");
		}
	}

	/**
	 * 获取员工信息
	 */
	@Override
	public Result findStaff(Long id) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		//校验参数
		if(null==id) {
			result.setMessage("主键不能为空");
			return result;
		}
		try {
			//获取员工表信息
			ResultUtil.setResult(result, RespCode.Code.SUCCESS,staffMapper.selectStaffById(id));
		} catch (Exception e) {
			log.error("查询员工失败(StaffServiceImpl.findStaff).......................",e);
		}
		return result;
	}

	/**
	 * 获取员工集合
	 */
	@Override
	public Result findStaffList(FindStaffListDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		//校验参数
		if(dto!=null) {
			String errorStr = dto.validateForm();
			if(StringUtils.isNotBlank(errorStr)) {
				result.setMessage(errorStr);
				return result;
			}
		}
		try {
			ResultUtil.setResult(result, RespCode.Code.SUCCESS,staffMapper.selectStaffList(dto));
		} catch (Exception e) {
			log.error("查询员工失败（StaffServiceImpl.findStaffList）.......................",e);
		}
		return result;
	}
	
	/**
	 * 修改员工
	 */
	@Override
	@Transactional
	public Result updateStaff(CompanyStaffDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		//校验参数
		if(dto==null||dto.getUserId()==null) {
			return ResultUtil.getResult(RespCode.Code.REQUEST_DATA_ERROR);
		}
		String errorStr = dto.validateForm();
		if(StringUtils.isNotBlank(errorStr)) {
			result.setMessage(errorStr);
			return result;
		}
		//首先保存用户
		User user = new User();
		//转换对应的用户数据
		BeanUtils.copyProperties(dto, user);
		user.setUpdateTime(new Date());
		user.setId(dto.getUserId());
		user.setSysStatus(new Byte(dto.getSysStatus()));
		try {
			userMapper.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			log.error("修改用户失败（StaffServiceImpl.addStaff）", e);
			throw new PermissionException(PermissionExceptionEnum.ADD_STAFF_ERROR);
		}
		//用户没得问题  然后保存员工
		CompanyStaff staff = new CompanyStaff();
		//转换对应的员工数据
		BeanUtils.copyProperties(dto, staff);
		//设置时间
		staff.setUpdateTime(new Date());
		//设置创建人
		try {
			  staffMapper.updateByPrimaryKeySelective(staff);
		} catch (Exception e) {
			log.error("修改员工失败（StaffServiceImpl.addStaff）", e);
			throw new PermissionException(PermissionExceptionEnum.ADD_STAFF_ERROR);
		}
		//最后保存职位员工中间表
		PositionStaffDTO posSta = new PositionStaffDTO();
		posSta.setPositionId(dto.getPositionId());
		posSta.setCreaterId(dto.getUpdaterId());
		posSta.setCreateTime(new Date());
		posSta.setUserId(user.getId());
		try {
			 staffMapper.updatePositionStaff(posSta);
		} catch (Exception e) {
			log.error("修改职位员工失败（StaffServiceImpl.addStaff）", e);
			throw new PermissionException(PermissionExceptionEnum.ADD_STAFF_ERROR);
		}
		return ResultUtil.setResult(result,RespCode.Code.SUCCESS);
	}

	/**
	 * 添加员工
	 */
	@Override
	@Transactional
	public Result addStaff(CompanyStaffDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		//校验参数
		if(dto==null||dto.getId()!=null) {
			return ResultUtil.getResult(RespCode.Code.REQUEST_DATA_ERROR);
		}
		String errorStr = dto.validateForm();
		if(StringUtils.isNotBlank(errorStr)) {
			result.setMessage(errorStr);
			return result;
		}
		//首先保存用户
		User user = new User();
		//转换对应的用户数据
		BeanUtils.copyProperties(dto, user);
		user.setCreateTime(new Date());
		user.setPassword("123456");
		user.setSysStatus(new Byte(dto.getSysStatus()));
		user.setStatus(new Byte("1"));
		user.setIsAppNotice(0);
		user.setIsWebNotice(0);
		user.setSourceType("1");
		user.setIsDelete(new Byte("0"));
		try {
			userMapper.insertUseGeneratedKeys(user);
		} catch (Exception e) {
			log.error("添加用户失败（StaffServiceImpl.addStaff）", e);
			throw new PermissionException(PermissionExceptionEnum.ADD_STAFF_ERROR);
		}
		//添加角色关系
		UserRoleDTO cRole  = new UserRoleDTO();
		cRole.setCreaterId(dto.getCreaterId());
		cRole.setCreateTime(new Date());
		cRole.setRoleId(2L);//默认角色2
		cRole.setUserId(user.getId());
		try {
			userMapper.insertRoleUser(cRole);
		} catch (Exception e) {
			log.error("添加角色关系失败（StaffServiceImpl.addStaff）", e);
			throw new PermissionException(PermissionExceptionEnum.ADD_STAFF_ERROR);
		}
		//用户没得问题  然后保存员工
		CompanyStaff staff = new CompanyStaff();
		//转换对应的员工数据
		BeanUtils.copyProperties(dto, staff);
		//设置时间
		staff.setCreateTime(new Date());
		//设置创建人
		staff.setId(null);
		staff.setUserId(user.getId());
		try {
			  staffMapper.insertSelective(staff);
		} catch (Exception e) {
			log.error("添加员工失败（StaffServiceImpl.addStaff）", e);
			throw new PermissionException(PermissionExceptionEnum.ADD_STAFF_ERROR);
		}
		//最后添加职位员工中间表
		PositionStaffDTO posSta = new PositionStaffDTO();
		posSta.setPositionId(dto.getPositionId());
		posSta.setCreateTime(new Date());
		posSta.setUserId(user.getId());
		posSta.setCreaterId(dto.getCreaterId());
		try {
			 staffMapper.insertPositionStaff(posSta);
		} catch (Exception e) {
			log.error("添加职位员工中间表失败（StaffServiceImpl.addStaff）", e);
			throw new PermissionException(PermissionExceptionEnum.ADD_STAFF_ERROR);
		}
		return ResultUtil.setResult(result,RespCode.Code.SUCCESS);
	}

	/**
	 * 逻辑删除员工
	 * @param Long 员工ID  Long 用户ID 
	 */
	@Override
	@Transactional
	public Result deleteStaff(Long userId ) {
		//校验参数
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		if(userId==null) {
			return ResultUtil.getResult(RespCode.Code.REQUEST_DATA_ERROR);
		}
		try {
			//获取这个用户角色权限
			CompanyRole role = new CompanyRole();
			role.setId(userId);
			List<RoleVO> rolelist = roleMapper.selectRoleListByUserId(userId);
			if(rolelist!=null) {
				if(rolelist.get(0).getIsSuperManager()==1) {
					return ResultUtil.getResult(RespCode.Code.UNAUTHORIZED);
				}
			}
			//删除用户
			DeteleUserDTO deteleUserDTO = new DeteleUserDTO();
			deteleUserDTO.setId(userId);
			deteleUserDTO.setUpdaterId(1L);
			deteleUserDTO.setUpdateTime(new Date());
			userMapper.updateUser(deteleUserDTO);
//			//删除员工
//			DeteleStaffDTO deteleStaffDTO = new DeteleStaffDTO();
//			deteleStaffDTO.setId(id);
//			deteleStaffDTO.setUpdaterId(1L);
//			deteleStaffDTO.setUpdateTime(new Date());
//			staffMapper.updateStaff(deteleStaffDTO);
			//返回消息
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除员工失败（StaffServiceImpl.deleteStaff）",e);
			throw new PermissionException(PermissionExceptionEnum.DETELE_STAFF_ERROR);
		}
	}
	
	@Override
	public Result getPageList(FindStaffListDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		try {
		 	PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
	        List<CompanyStaffVO> list = staffMapper.selectStaffList(dto);
	        PageInfo<CompanyStaffVO> pageInfo = new PageInfo<>(list);
	        result.setData(pageInfo.getList());
			result.setCount(pageInfo.getTotal());
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS);
		} catch (Exception e) {
			log.error("查询分页数据异常", e);
		}
        return result;
	}

	@Override
	@Transactional
	public Result updateUser(CompanyStaffDTO dto) {
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		//校验参数
		if(dto==null||dto.getUserId()==null) {
			return ResultUtil.getResult(RespCode.Code.REQUEST_DATA_ERROR);
		}
		String errorStr = dto.validateForm();
		if(StringUtils.isNotBlank(errorStr)) {
			result.setMessage(errorStr);
			return result;
		}
		//首先保存用户
		User user = new User();
		//转换对应的用户数据
		BeanUtils.copyProperties(dto, user);
		user.setUpdateTime(new Date());
		user.setId(dto.getUserId());
		user.setStatus(new Byte(dto.getStatus()));
		try {
			ResultUtil.setResult(result, RespCode.Code.SUCCESS,userMapper.updateByPrimaryKeySelective(user));
		} catch (Exception e) {
			log.error("修改用户失败（StaffServiceImpl.updateUser）", e);
			throw new PermissionException(PermissionExceptionEnum.ADD_STAFF_ERROR);
		}
		return result;
	}
}
