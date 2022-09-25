package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * UI界面访问路径为：http://localhost:8080/javaproject/swagger-ui.html
 * 设置只在开发环境或者测试环境有效，生产环境无效，使用@Profile注解
 *
 */

@Configuration
@EnableSwagger2
//@Profile({"dev", "test"})
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //基本信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("使用Swagger2生成接口文档说明")
                .description("林佳岸-线上英语学习系统")
                .version("2.0")
                .build();
    }



}
