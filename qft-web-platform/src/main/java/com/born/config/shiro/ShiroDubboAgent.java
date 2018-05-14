package com.born.config.shiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.born.config.DubboAgent;
import com.born.facade.service.ICompanyRoleService;
import com.born.facade.service.IMenuAuthorityService;
import com.born.facade.service.IMenuService;
import com.born.facade.service.IUserService;

/**
 * 
* @ClassName: ShiroDubboAgent 
* @Description: shiro的dubbo代理，获取服务提供者（接口） 
* @author lijie 
* @date 2018年4月28日 上午10:40:33 
*
 */
@Configuration
public class ShiroDubboAgent {
	
	@Autowired
	private DubboAgent dubboAgent;

    /**
     * 
    * @Title: getIMenuService 
    * @Description: 获取菜单接口 
    * @param @return    设定文件 
    * @return ICompanyMenuService    返回类型 
    * @author lijie
    * @throws
     */
	@Bean
	public IMenuService getIMenuService() {
		return dubboAgent.getReference(IMenuService.class);
	}
	/**
	 * 
	* @Title: getIWebUserService 
	* @Description: 获取用户服务 
	* @param @return    设定文件 
	* @return IWebUserService    返回类型 
	* @author lijie
	* @throws
	 */
	@Bean
	public IUserService getIWebUserService(){
		return dubboAgent.getReference(IUserService.class);
	}
    /**
     * 
    * @Title: getIMenuAuthorityService 
    * @Description: 获取菜单权限接口
    * @param @return    设定文件 
    * @return IMenuAuthorityService    返回类型 
    * @author lijie
    * @throws
     */
	@Bean
	public IMenuAuthorityService getIMenuAuthorityService() {
		return dubboAgent.getReference(IMenuAuthorityService.class);
	}

    /**
     * 
    * @Title: getIRoleService 
    * @Description: 获取角色Service接口
    * @param @return    设定文件 
    * @return ICompanyRoleService    返回类型 
    * @author lijie
    * @throws
     */
	@Bean
	public ICompanyRoleService getIRoleService() {
		return dubboAgent.getReference(ICompanyRoleService.class);
	}

}
