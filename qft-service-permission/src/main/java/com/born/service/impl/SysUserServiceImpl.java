package com.born.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.born.facade.service.ISysUserService;
import com.born.facade.vo.UserApiVO;
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
	public Result getUser(String account) {
		log.info("get user info for account={}", account);
		try {
			UserApiVO user = sysUserMapper.selectUserByAccount(account);
			return ResultUtil.getResult(RespCode.Code.SUCCESS, user);
		} catch (Exception e) {
			log.error("根据用户异常", e);
			return ResultUtil.getResult(RespCode.Code.FAIL);
		}
	}

}
