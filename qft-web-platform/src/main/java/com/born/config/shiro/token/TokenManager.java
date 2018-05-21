package com.born.config.shiro.token;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;

import com.born.config.shiro.CustomShiroRealm;
import com.born.facade.vo.UserInfoVO;



/**
 * 
* @ClassName: TokenManager 
* @Description: Shiro管理下的Token工具类
* @author lijie 
* @date 2018年5月7日 下午3:54:44 
*
 */
public class TokenManager {
	
	/**
	 * 
	* @Title: getLoginUser 
	* @Description: 用户信息管理
	* @param @return    设定文件 
	* @return UserInfoVO    返回类型 
	* @author lijie
	* @throws
	 */
	public static UserInfoVO getLoginUser() {
		return (UserInfoVO) SecurityUtils.getSubject().getPrincipal();
	}
	
	/**
	 * 
	* @Title: clearAuth 
	* @Description:清楚权限
	* @param     设定文件 
	* @return void    返回类型 
	* @author lijie
	* @throws
	 */
	public static void clearAuth() {
		RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		CustomShiroRealm realm = (CustomShiroRealm) rsm.getRealms().iterator().next();
		realm.clearAuthz();
	}
}
