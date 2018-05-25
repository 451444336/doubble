package com.born.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.entity.CompanyRole;
import com.born.facade.service.ICompanyRoleAuthService;
import com.born.facade.vo.appauth.UserRoleAuthVO;
import com.born.facade.vo.appauth.UserRoleMenuVo;
import com.born.mapper.CompanyMenuMapper;
import com.born.mapper.CompanyRoleMapper;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @ClassName: CompanyRoleAuthServiceImpl
 * @Description: App端获取角色菜单权限实现类
 * @author 张永胜
 * @date 2018年5月4日 下午6:22:51
 * @version 1.0
 */
@Slf4j
@Service(version = "1.0.0")
public class CompanyRoleAuthServiceImpl implements ICompanyRoleAuthService {

	/**
	 * 角色服务
	 */
	@Autowired
	private CompanyRoleMapper companyRoleMapper;

	/**
	 * 角色菜单权限服务
	 */
	@Autowired
	private CompanyMenuMapper companyMenuMapper;

	@Override
	public Result findRoleAuthList(String userId) {
		log.info("get role auth info for userId = {}", userId);

		if (userId == null) {
			return ResultUtil.getResult(RespCode.Code.FAIL, "参数不能为空");
		}

		try {

			List<CompanyRole> rolelist = companyRoleMapper.selectRoleByUserId(userId);

			if (rolelist.size() == 0) {
				return ResultUtil.getResult(RespCode.Code.FAIL, "没有查询到角色");
			}

			/**
			 * 这里封装的是返回给APP的权限信息
			 */
			List<UserRoleAuthVO> roleAuth = new ArrayList<UserRoleAuthVO>();

			for (CompanyRole role : rolelist) {
				/**
				 * 级联获取角色菜单以及菜单权限表
				 */
				String roleId = String.valueOf(role.getId());
				Set<UserRoleMenuVo> roleMenuList = companyMenuMapper.selectMenuByRoleId(roleId);

				UserRoleAuthVO userRoleAuth = new UserRoleAuthVO();
				userRoleAuth.setId(roleId);
				userRoleAuth.setRoleName(role.getRoleName());
				userRoleAuth.setIsAuthEdit(role.getIsAuthEdit());
				userRoleAuth.setMenulist(roleMenuList);

				roleAuth.add(userRoleAuth);
			}

			return ResultUtil.getResult(RespCode.Code.SUCCESS, roleAuth);
		} catch (Exception e) {
			log.error("查询权限异常 ", e);
			return ResultUtil.getResult(RespCode.Code.FAIL, "查询权限异常");
		}

	}

}
