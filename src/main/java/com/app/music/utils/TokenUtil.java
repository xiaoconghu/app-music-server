package com.app.music.utils;

import com.app.music.config.CookieUtils;
import com.app.music.entity.User;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;

@Component
public class TokenUtil {
    @Autowired
    LoadingCache loadingCache;

    public User getUser() {
        String token = CookieUtils.getCookieValue("token");
        User user = (User) loadingCache.get(token);
        return user;
    }

}
