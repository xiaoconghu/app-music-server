package com.app.music.utils;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TokenCacheService {

    /**
     * 如果缓存中有则直接走缓存，如果没有则执行方法，将方法的返回值作为缓存
     *
     * @param token
     * @param userId
     * @return
     */
    @Cacheable(value = "cache-test",key = "'getName:' + #p0")
    public static String getTokenObject(String token, String userId) {
        return userId;
    }

    /**
     * 将方法的返回值更新到缓存
     *
     * @param token
     * @param userId
     * @return
     */
    @CachePut(value = "cache-test", key = "'getName:' + #p0")
    public static String updateName(String token, String userId) {
        System.out.println("更新名称");
        return userId;
    }

    /**
     * 删除缓存
     *
     * @param token
     */
    @CacheEvict(value = "cache-test", key = "'getName:' + #p0")
    public static void deleteToken(String token) {
        System.out.println("删除名称");
    }
}