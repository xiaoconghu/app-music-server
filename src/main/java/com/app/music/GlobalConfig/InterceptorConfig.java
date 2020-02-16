package com.app.music.GlobalConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Autowired
    ParamInterceptor paramInterceptor;
//    public void addInterceptors(InterceptorRegistry registry) {
//    	//此处配置拦截路径
//        registry.addInterceptor(new ParamInterceptor()).addPathPatterns("/**");
//    }

    /**
     * 添加token拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(paramInterceptor)
                //选择过滤哪些接口
                .addPathPatterns("/**")
                //选择忽略的接口
                .excludePathPatterns("/music/user/login");
        super.addInterceptors(registry);
    }

}