package com.wx.demo.config;

import com.wx.demo.interceptor.MyCartInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wangxing
 * @date 2021/4/20 15:30
 */
@Configuration
public class MyCartMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyCartInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/index","/login","/css/**","/images/**","/js/**");
    }
}
