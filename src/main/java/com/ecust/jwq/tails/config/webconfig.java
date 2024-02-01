package com.ecust.jwq.tails.config;

import com.ecust.jwq.tails.interceptor.LoginCheckinterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration//配置类
public class webconfig  implements WebMvcConfigurer {
    @Autowired
    private LoginCheckinterceptor loginCheckinterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         registry.addInterceptor(loginCheckinterceptor).addPathPatterns("/**");
    }
}
