package com.book.bookmanager.config;

import com.book.bookmanager.interceptor.BorrowInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 跨域配置和文件上传路径映射
 */
/*
WebMvcConfigurer配置类其实是Spring内部的一种配置方式，采用JavaBean的形式来代替传统的xml配置文件形式进行针对框架个性化定制，可以自定义一些Handler，Interceptor，ViewResolver，MessageConverter。
基于java-based方式的spring mvc配置，需要创建一个配置类并实现WebMvcConfigurer 接口；
 */
@Configuration
public class CrossConfiguration implements WebMvcConfigurer {

    @Value("${file.upload.path}")
    private String uploadPath;

    //@Resource
    //private BorrowInterceptor borrowInterceptor;

    /*
     addCorsMappings：跨域
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许跨域的路径
                .allowedOriginPatterns("*") // 允许跨域的源
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE") // 允许请求方法
                .maxAge(168000) // 预检间隔时间
                .allowedHeaders("*") // 允许头部设置
                .allowCredentials(true); // 是否发送cookie
    }
/*
    自定义静态资源映射目录，只需重写addResourceHandlers方法即可
    addResourceHandler：指的是对外暴露的访问路径
    addResourceLocations：指的是内部文件放置的目录
    在下面配置中，访问uploads/下的文件会被映射到本地项目uploadPath下的目录里面。
 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:"+uploadPath);
    }
//    静态文件访问一般会有跨域问题，所以配置允许跨域。

    //@Override
    //public void addInterceptors(InterceptorRegistry registry) {
    //    registry.addInterceptor(borrowInterceptor).addPathPatterns("/borrow/**");
    //}
}
