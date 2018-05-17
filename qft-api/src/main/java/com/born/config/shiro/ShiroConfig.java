package com.born.config.shiro;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.born.config.auth.AccessTokenFilter;
import com.born.util.ShiroReadUitl;

@Configuration
public class ShiroConfig {
	
	/**
	 * 程序启动时就执行
	 * 
	 * @Title: shirFilter
	 * @Description: Shiro配置
	 * @param securityManager
	 * @return
	 * @author 张永胜
	 * @return ShiroFilterFactoryBean
	 * @date 2018年5月4日 下午6:37:23
	 */
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {

		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 自定义过滤器
		Map<String, Filter> filterMap = shiroFilterFactoryBean.getFilters();
		filterMap.put("token", accessTokenFilter());
		shiroFilterFactoryBean.setFilters(filterMap);

		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

		
		/**
		 * 获取配置文件中的规则
		 * 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 :这是一个坑，一不小心代码就不好使了; 
		 * authc:所有url都必须认证通过才可以访问;
		 * anon:所有url都都可以匿名访问
		 * token:所有接口必须授权
		 */
		LinkedHashMap<String, LinkedHashMap<String, String>> shrioList = ShiroReadUitl.readShrio();
		Iterator<Entry<String, LinkedHashMap<String, String>>> iterator = shrioList.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, LinkedHashMap<String, String>> entry = iterator.next();
			Iterator<Entry<String, String>> v = entry.getValue().entrySet().iterator();
			while (v.hasNext()) {
				Entry<String, String> res = v.next();
				//配置不会被拦截的链接 顺序判断
				filterChainDefinitionMap.put(res.getKey(), res.getValue());
			}
		}

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	/**
	 * 
	 * @Title: registration
	 * @Description: 注意：如果这里不配置的话，不需要token验证的地址都会验证
	 * @param filter
	 * @return
	 * @author 张永胜
	 * @return FilterRegistrationBean
	 * @date 2018年5月4日 下午6:38:02
	 */
	@Bean
	public FilterRegistrationBean registration(AccessTokenFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean(filter);
		registration.setEnabled(false);
		return registration;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(myShiroRealm());
		securityManager.setRealm(myShiroRealm());
		return securityManager;
	}

	@Bean
	public MyShiroRealm myShiroRealm() {
		return new MyShiroRealm();
	}

	@Bean
	public AccessTokenFilter accessTokenFilter() {
		return new AccessTokenFilter();
	}
}
