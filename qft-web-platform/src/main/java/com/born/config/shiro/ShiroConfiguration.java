package com.born.config.shiro;


import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import lombok.extern.slf4j.Slf4j;

/**
 * 
* @ClassName: ShiroConfiguration 
* @Description: Shiro 配置
* @author lijie 
* @date 2018年4月28日 上午10:19:14 
*
 */
@Configuration
@Slf4j
public class ShiroConfiguration {
	/**
	 * 
	* @Title: shiroDialect 
	* @Description: shiroDialect bean 配置
	* @param @return    设定文件 
	* @return ShiroDialect    返回类型 
	* @author lijie
	* @throws
	 */
	@Bean(name = "shiroDialect")
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}
    /**
     * 
    * @Title: getEhCacheManager 
    * @Description: 加载cache 配置 
    * @param @return    设定文件 
    * @return EhCacheManager    返回类型 
    * @author lijie
    * @throws
     */
	@Bean
	public EhCacheManager getEhCacheManager() {
		EhCacheManager em = new EhCacheManager();
		em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
		return em;
	}
    /**
     * 
    * @Title: myShiroRealm 
    * @Description: Shiro Realm bean 配置
    * @param @param cacheManager
    * @param @return    设定文件 
    * @return MyShiroRealm    返回类型 
    * @author lijie
    * @throws
     */
	@Bean(name = "myShiroRealm")
	public MyShiroRealm myShiroRealm(EhCacheManager cacheManager) {
		MyShiroRealm realm = new MyShiroRealm();
		realm.setCacheManager(cacheManager);
		return realm;
	}
    /**
     * 
    * @Title: getSessionIdCookie 
    * @Description: session id cookie
    * @param @return    设定文件 
    * @return SimpleCookie    返回类型 
    * @author lijie
    * @throws
     */
	@Bean(name = "sessionIdCookie")
	public SimpleCookie getSessionIdCookie() {
		SimpleCookie cookie = new SimpleCookie("sid");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(-1);
		return cookie;
	}
    /**
     * 
    * @Title: getExecutorServiceSessionValidationScheduler 
    * @Description: session validation scheduler 回话失效校验
    * @param @return    设定文件 
    * @return ExecutorServiceSessionValidationScheduler    返回类型 
    * @author lijie
    * @throws
     */
	@Bean(name = "sessionValidationScheduler")
	public ExecutorServiceSessionValidationScheduler getExecutorServiceSessionValidationScheduler() {
		ExecutorServiceSessionValidationScheduler scheduler = new ExecutorServiceSessionValidationScheduler();
		scheduler.setInterval(900000);
		return scheduler;
	}

    /**
     * 
    * @Title: defaultWebSessionManager 
    * @Description: session共享管理
    * @param @param cacheManager
    * @param @return    设定文件 
    * @return DefaultWebSessionManager    返回类型 
    * @author lijie
    * @throws
     */
	@Bean(name = "sessionManager")
	public DefaultWebSessionManager defaultWebSessionManager(EhCacheManager cacheManager) {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setCacheManager(cacheManager);
		sessionManager.setGlobalSessionTimeout(1800000);
		sessionManager.setSessionValidationScheduler(getExecutorServiceSessionValidationScheduler());
		sessionManager.setSessionIdCookieEnabled(true);
		sessionManager.setDeleteInvalidSessions(true);
		sessionManager.setSessionValidationSchedulerEnabled(true);
		return sessionManager;
	}

   /**
    * 
   * @Title: rememberMeCookie 
   * @Description: cookie对象
   * @param @return    设定文件 
   * @return SimpleCookie    返回类型 
   * @author lijie
   * @throws
    */
	@Bean
	public SimpleCookie rememberMeCookie() {
		log.info("ShiroConfiguration.rememberMeCookie()");
		// 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		simpleCookie.setHttpOnly(true);
		// <!-- 记住我cookie生效时间30天 ,单位秒;-->
		simpleCookie.setMaxAge(259200);
		return simpleCookie;
	}

    /**
     * 
    * @Title: rememberMeManager 
    * @Description: cookie管理对象
    * @param @return    设定文件 
    * @return CookieRememberMeManager    返回类型 
    * @author lijie
    * @throws
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
    	log.info("ShiroConfiguration.rememberMeManager()");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }

    /**
     * 
    * @Title: getDefaultWebSecurityManager 
    * @Description: 授权校验
    * @param @param myShiroRealm
    * @param @param sessionManager
    * @param @return    设定文件 
    * @return DefaultWebSecurityManager    返回类型 
    * @author lijie
    * @throws
     */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(MyShiroRealm myShiroRealm,
			DefaultWebSessionManager sessionManager) {
		DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
		dwsm.setRealm(myShiroRealm);
		// 用户授权/认证信息Cache, 采用EhCache
		dwsm.setCacheManager(getEhCacheManager());
		// 注入记住我管理器
		dwsm.setRememberMeManager(rememberMeManager());
		// session共享管理器
		dwsm.setSessionManager(sessionManager);
		return dwsm;
	}
	/**
	 * 
	* @Title: getAuthorizationAttributeSourceAdvisor 
	* @Description: authorization attribute source advisor
	* @param @param securityManager
	* @param @return    设定文件 
	* @return AuthorizationAttributeSourceAdvisor    返回类型 
	* @author lijie
	* @throws
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(
			DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(securityManager);
		return aasa;
	}
	/**
	 * 
	* @Title: getLifecycleBeanPostProcessor 
	* @Description: Lifecycle Bean post processor 
	* @param @return    设定文件 
	* @return LifecycleBeanPostProcessor    返回类型 
	* @author lijie
	* @throws
	 */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    /**
     * 
    * @Title: getDefaultAdvisorAutoProxyCreator 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @return    设定文件 
    * @return DefaultAdvisorAutoProxyCreator    返回类型 
    * @author lijie
    * @throws
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

   /**
    * 
   * @Title: getShiroFilterFactoryBean 
   * @Description: 权限过滤器 
   * @param @param securityManager
   * @param @param myShiroRealm
   * @param @return    设定文件 
   * @return ShiroFilterFactoryBean    返回类型 
   * @author lijie
   * @throws
    */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager,
			MyShiroRealm myShiroRealm) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 从数据库读取权限规则，加载到shiroFilter中
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		// filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/*", "anon");
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/doLogin", "anon");

		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后要跳转的连接
		shiroFilterFactoryBean.setSuccessUrl("/index");
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		return shiroFilterFactoryBean;
	}

}
