package com.born.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
 
/**
 * 
* @ClassName: WebConfig 
* @Description: 后台web项目配置
* @author lijie 
* @date 2018年5月7日 下午5:49:51 
*
 */
@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	 
    /**
     * 静态资源拦截器
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**", "/upload/**", "/login/**","/*", "/favicon.ico").addResourceLocations("classpath:/upload/", "classpath:/static/", "/favicon.ico");
        super.addResourceHandlers(registry);
    }
 
}
