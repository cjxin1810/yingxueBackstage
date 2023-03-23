package com.cjx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        //api帮助文档的描述信息，information
        ApiInfo apiInfo = new ApiInfoBuilder()
                .contact(//配置swagger文档主体内容
                        new Contact("swagger开发文档",//文档的发布者名称
                                "http:www.bjsxt.com",//文档发布者的网站地址
                                "admin@chenxin.com")//文档发布者的电子邮箱
                )
                .title("swagger框架学习帮助文档")
                .description("swagger框架学习帮助文档详细描述")
                .version("1.1")
                .build();
        //给docket上下文配置api描述信息
        docket.apiInfo(apiInfo);
        docket
                .select()//获取docket中的选择器，返回ApiselectorBuilder.构建选择器的。如：扫描什么包的注解
                .apis(RequestHandlerSelectors.basePackage("com.cjx.controller"));
        return docket;
    }

}
