package com.movie2.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@Slf4j
public class MyConstants extends WebMvcConfigurationSupport {
    // 拦截器
    // swagger
    public static final String MY_URL="http://localhost:8888";

    @Value("${cxy.poster.upload}")
    private String uploadPath; //获得application.properties配置的本地路径

    //静态资源目录,文件上传的相对目录（通过项目访问到本地的目录）
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        //                    项目的路径，/                                           file:本地目录
        //                    根目录， ** 该目录下的所有子目录，子文件                 classpath:项目根目录
        registry.addResourceHandler("/images/**").addResourceLocations("file:"+uploadPath);//配置本地文件夹
        registry.addResourceHandler("/**").addResourceLocations( "classpath:/static/");//配置static路径
        log.info("自定义静态资源目录、此处功能用于文件映射");
    }
}
