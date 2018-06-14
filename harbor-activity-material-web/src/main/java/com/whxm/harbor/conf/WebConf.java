package com.whxm.harbor.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConf extends WebMvcConfigurerAdapter {
    @Autowired
    private HandlerInterceptor tokenInterceptor;


    //在低版本的spring boot中WebConf extends WebMvcConfigurerAdapter时,如下写法
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/*")
                .excludePathPatterns("/activityMaterials")
                .excludePathPatterns("/picture");
        super.addInterceptors(registry);
    }

    // 跨域请求 (ip 不同  ||port 不同) == CrosAccess  // jsonp (过时的实现)
    //  新的实现方式  两个支持 ： 服务支持 + 浏览器支持（谷歌、火狐，IE10 之后的版本 都支持）

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
                .allowCredentials(true)
                .allowedOrigins("*"); // 服务器的ip:port
        super.addCorsMappings(registry);
    }
}