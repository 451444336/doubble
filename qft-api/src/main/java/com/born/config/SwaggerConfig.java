package com.born.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
* @ClassName: SwaggerConfig  
* @Description: swagger配置
* @author lijie  
* @date 2018年4月25日  
*
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())//调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.born.controller"))
                .paths(PathSelectors.regex("/api/.*"))
                .build();
    }
    
    private ApiInfo apiInfo() {
        Contact contact = new Contact("张永胜","www.baidu.com","547107882@qq.com");
        return new ApiInfoBuilder()
                .title("APP接口文档")
                .description("注意：Token传输放在Header中，auth 为header中的key，"
                		+ "用返回时的userId_token 拼接为值,除了登录注册接口，"
                		+ "其余所有接口必须携带timestamp(时间戳)、nonc(随机数)、sign(签名 规则[auth(值), nonc，timestamp, userId]) ")
                .contact(contact)
                .version("2.0")
                .license("AQ")
                .build();
    }
}
