package com.app.music.GlobalConfig;

import com.app.music.config.CookieUtils;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ParamInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private LoadingCache loadingCache;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = CookieUtils.getCookieValue("token");
        if (StringUtils.isEmpty(token)) {
            httpServletResponse.sendError(401, "token验证失败");
            return false;
        }
        Object userId = loadingCache.get(token);
        if (userId != null) {
            System.out.println("token验证成功");
            System.out.println(userId);
            return true;
        } else {
            httpServletResponse.sendError(401, "token验证失败");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}