package com.born.dto;

import java.util.List;

import com.born.config.auth.token.Token;
import com.born.facade.vo.appauth.UserRoleAuthVO;

import lombok.Data;

/**
 * 返回给APP端封装的信息 可以扩展
 * 
 * @author zys
 *
 */
@Data
public class ResultInfo {

	/**
	 * token信息
	 */
	private Token token;

	/**
	 * 用户必要信息
	 */
	private UserInfoVO userInfo;

	/**
	 * 角色权限
	 */
	private List<UserRoleAuthVO> roleAuthList;

}
