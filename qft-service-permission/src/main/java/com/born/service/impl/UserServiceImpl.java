package com.born.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.born.facade.constant.UserEnum;
import com.born.facade.dto.StaffOperDTO;
import com.born.facade.dto.user.LoginDTO;
import com.born.facade.dto.user.UserDTO;
import com.born.facade.entity.CompanyStaff;
import com.born.facade.entity.CompanyStaffOper;
import com.born.facade.entity.User;
import com.born.facade.exception.PermissionException;
import com.born.facade.exception.PermissionExceptionEnum;
import com.born.facade.service.IUserService;
import com.born.facade.vo.RoleVO;
import com.born.facade.vo.UserInfoVO;
import com.born.facade.vo.UserRoleVO;
import com.born.mapper.CompanyRoleMapper;
import com.born.mapper.CompanyStaffMapper;
import com.born.mapper.CompanyStaffOperMapper;
import com.born.mapper.SysUserMapper;
import com.born.util.encrypt.security.SecurityUtil;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;

import lombok.extern.slf4j.Slf4j;
/**
 * 
* @ClassName: WebUserServiceImpl 
* @Description: web用户相关操作
* @author lijie 
* @date 2018年5月2日 下午2:18:15 
*
 */
@Slf4j
@Service(version = "1.0.0")
public class UserServiceImpl implements IUserService {

	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private CompanyStaffOperMapper staffOperMapper;
	
	@Autowired
	private CompanyStaffMapper companyStaffMapper;
	
	@Autowired
	private CompanyRoleMapper companyRoleMapper;
	
	@Override
	public Result getUserByCondition(UserDTO userDto) {
		log.info("根据条件查询管理员信息入参, userDto={}", JSON.toJSONString(userDto));
		try {
			return ResultUtil.getResult(RespCode.Code.SUCCESS, sysUserMapper.selectUserByCondition(userDto));
		} catch (Exception e) {
			log.error("根据条件查询管理员信息异常", e);
			return ResultUtil.getResult(RespCode.Code.FAIL);
		}
	}

	@Override
	public Result login(LoginDTO loginDto) {
		log.info("用户登录入参={}", JSON.toJSONString(loginDto));
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		String errorStr = loginDto.validateForm();
		if (StringUtils.isNotBlank(errorStr)) {
			return ResultUtil.setResult(result, RespCode.Code.REQUEST_DATA_ERROR, errorStr);
		}
		try {
			User user = new User();
			user.setAccount(loginDto.getAccount());
			// 此处需要修改状态为常量
			user.setIsDelete(UserEnum.NOT_DELETE.getStatus());
			user = sysUserMapper.selectOne(user);
			// 校验账户密码
			if (null == user) {
				return ResultUtil.setResult(result, PermissionExceptionEnum.USER_NON_EXISTENT);
			}
			if (!loginDto.getPassword().equals(user.getPassword())) {
				return ResultUtil.setResult(result, PermissionExceptionEnum.AUTHENTICATION);
			}
			if (UserEnum.LOGOUT.getStatus().equals(user.getStatus())) {
				return ResultUtil.setResult(result, PermissionExceptionEnum.USER_LOGOUT);
			}
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS, getUserInfo(user));
		} catch (Exception e) {
			log.error("用户登录异常", e);
		}
		return result;
	}
	/**
	 * 
	* @Title: getSessionUserVO 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param user
	* @param @return    设定文件 
	* @return SessionUserVO    返回类型 
	* @author lijie
	* @throws
	 */
	private UserInfoVO getUserInfo(final User user) {
		UserInfoVO result = new UserInfoVO();
		// 查询员工信息
		CompanyStaff cs = new CompanyStaff();
		cs.setUserId(user.getId());
		cs = companyStaffMapper.selectOne(cs);
		result.setAccount(user.getAccount());
		result.setId(user.getId());
		if (null != cs) {
			result.setUserName(cs.getRealname());
		}
		// 查询角色信息
		List<RoleVO> rs = companyRoleMapper.selectRoleListByUserId(user.getId());
		if (CollectionUtils.isNotEmpty(rs)) {
			List<UserRoleVO> roles = new ArrayList<>(rs.size());
			UserRoleVO ur;
			for (RoleVO r : rs) {
				ur = new UserRoleVO();
				ur.setId(r.getId());
				ur.setRoleName(r.getRoleName());
				roles.add(ur);
			}
			result.setRoles(roles);
		}
		return result;
	}
	
	@Override
	@Transactional
	public Result changeStaffOper(StaffOperDTO staffOperDTO) {
		log.info("添加/修改用户操作信息入参={}", JSON.toJSONString(staffOperDTO));
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		String errorStr = staffOperDTO.validateForm();
		if (StringUtils.isNotBlank(errorStr)) {
			return ResultUtil.setResult(result, RespCode.Code.REQUEST_DATA_ERROR, errorStr);
		}
		try {
			// 跟新操作时间
			CompanyStaffOper staffOper = new CompanyStaffOper();
			Date date = new Date();
			staffOper.setUserId(staffOperDTO.getUserId());
			staffOper = staffOperMapper.selectOne(staffOper);
			CompanyStaffOper record = new CompanyStaffOper();
			record.setLastIp(staffOperDTO.getLastIp());
			record.setLastTime(date);
			if (null == staffOper) {
				record.setUserId(staffOperDTO.getUserId());
				record.setCreaterId(staffOperDTO.getUserId());
				record.setCreateTime(date);
				staffOperMapper.insertUseGeneratedKeys(record);
			} else {
				record.setUpdaterId(staffOperDTO.getUserId());
				record.setUpdateTime(date);
				staffOperMapper.updateByPrimaryKeySelective(record);
			}
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS);
		} catch (Exception e) {
			log.error("添加/修改用户操作信息异常", e);
			throw new PermissionException(PermissionExceptionEnum.CHANGE_STAFF_OPER);
		}
	}

	@Override
	public Result updateUserPass(String id, String password, String account, String operatingId) {

		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("id", id);
			param.put("password", SecurityUtil.encryptPassword(account, password));
			param.put("updateTime", new Date());
			param.put("updaterId", operatingId);
			
			int result = sysUserMapper.updateUserPassById(param);
			
			if(result > 0) {
				return ResultUtil.getResult(RespCode.Code.SUCCESS);
			}
		} catch (Exception e) {
			log.error("修改密码异常", e);
		}
		
		return ResultUtil.getResult(RespCode.Code.FAIL);
	}

}
