package com.app.music.GlobalConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Autowired
    ParamInterceptor paramInterceptor;

    /**
     * 添加token拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(paramInterceptor)
                //选择过滤哪些接口
                .addPathPatterns("/music/**")
                //选择忽略的接口
                .excludePathPatterns("/music/user/login", "/static/**");
        super.addInterceptors(registry);
    }

}