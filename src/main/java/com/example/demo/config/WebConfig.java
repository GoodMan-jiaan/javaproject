package com.example.demo.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Value("${static-file.directory}")
    private String path;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**")
//                .addResourceLocations("file:D:/a_22_HangJia/graduation/videoesdownload/");

        registry.addResourceHandler("/static/**")
                .addResourceLocations("file:" + path);
//        registry.addResourceHandler("/videos/**")
//                .addResourceLocations("file:D:\\a_22_HangJia\\graduation\\videoesdownload\\");
//        registry.addResourceHandler("/**").addResourceLocations(
//                "classpath:/static/");
//        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
//                "classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations(
//                "classpath:/META-INF/resources/webjars/");
//        super.addResourceHandlers(registry);
    }

//
    /**
     * TODO 部署到生产环境的时候需要使用allowedOrigin设置允许访问白名单
     * @param registry
     */
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*").allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE", "PATCH")
                .maxAge(3600)
                .allowCredentials(true);
        super.addCorsMappings(registry);
    }


}
