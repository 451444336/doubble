package com.born.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.facade.entity.User;
import com.born.facade.service.ISysUserService;
import com.born.mapper.SysUserMapper;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @ClassName: SysUserServiceImpl
 * @Description: 用户操作类
 * @author 张永胜
 * @date 2018年5月4日 下午6:20:49
 * @version 1.0
 */
@Slf4j
@Service(version = "1.0.0")
public class SysUserServiceImpl implements ISysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public User getUser(String account) {
		log.info("get user info for account={}", account);
		return sysUserMapper.selectUserByAccount(account);
	}

	@Override
	public Result saveUser(User user) {
		try {

			if (user == null) {
				return ResultUtil.getResult(RespCode.Code.FAIL, "数据为空");
			}

			/**
			 * 查询注册账户是否已经存在了 数据库中逻辑删除，标识该账户是删除了的，不会判断账户已经注册
			 */
			int isExist = sysUserMapper.selectUserIsExist(user.getAccount());
			if (isExist > 0) {
				return ResultUtil.getResult(RespCode.Code.FAIL, "该账户已经注册");
			}
			user.setCreaterId((long) 10);
			user.setCreateTime(new Date());
			user.setIsAppNotice(0);// 0: 否
			user.setIsWebNotice(0);// 0: 否
			user.setIsEnableApp(1);// 1: APP登录开启
			user.setStatus((byte) 1);// 1: 标识正常
			user.setIsDelete((byte) 0);// 是否删除

			int result = sysUserMapper.insert(user);
			if (result > 0) {
				return ResultUtil.getResult(RespCode.Code.SUCCESS);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.getResult(RespCode.Code.INTERNAL_SERVER_ERROR);
		}

		return ResultUtil.getResult(RespCode.Code.FAIL);
	}

}
