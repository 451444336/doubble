package com.born.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.born.facade.service.IMenuAuthorityService;
import com.born.mapper.MenuAuthorityMapper;
import com.born.util.result.RespCode;
import com.born.util.result.Result;
import com.born.util.result.ResultUtil;

import lombok.extern.slf4j.Slf4j;
/**
 * 
* @ClassName: MenuAuthorityServiceImpl 
* @Description: 菜单权限 
* @author lijie 
* @date 2018年4月28日 下午4:01:10 
*
 */
@Slf4j
@Service(version = "1.0.0")
public class MenuAuthorityServiceImpl implements IMenuAuthorityService {
	
	@Autowired
	private MenuAuthorityMapper menuAuthorityMapper;
	
	@Override
	public Result getMenuAuthorityList(String companyId) {
		log.info("根据查询所有/公司菜单权限数据入参={}", companyId);
		try {
			return ResultUtil.getResult(RespCode.Code.SUCCESS, menuAuthorityMapper.selectMenuAuthorityList(companyId));
		} catch (Exception e) {
			log.error("根据查询所有/公司菜单权限数据异常", e);
			return ResultUtil.getResult(RespCode.Code.FAIL);
		}
	}

	@Override
	public Result getAuthorityListByMenuIds(List<Long> menuIds) {
		log.info("根据菜单ID集合查询菜单操作权限数据入参={}", JSON.toJSONString(menuIds));
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		if (CollectionUtils.isEmpty(menuIds)) {
			return ResultUtil.setResult(result, RespCode.Code.REQUEST_DATA_ERROR, "菜单ID不能为空");
		}
		try {
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS,
					menuAuthorityMapper.selectAuthorityListByMenuIds(menuIds));
		} catch (Exception e) {
			log.error("根据菜单ID集合查询菜单操作权限异常", e);
		}
		return result;
	}

	@Override
	public Result getAuthorityListByUserId(Long userId) {
		log.info("根据用户ID 查询操作权限数据入参={}", userId);
		Result result = ResultUtil.getResult(RespCode.Code.FAIL);
		if (null == userId) {
			return ResultUtil.setResult(result, RespCode.Code.REQUEST_DATA_ERROR, "用户ID不能为空");
		}
		try {
			return ResultUtil.setResult(result, RespCode.Code.SUCCESS,
					menuAuthorityMapper.selectAuthorityListByUserId(userId));
		} catch (Exception e) {
			log.error("根据用户ID 查询操作权限数据异常", e);
		}
		return result;
	}

}
