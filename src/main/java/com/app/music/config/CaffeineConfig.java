package com.app.music.config;

import com.github.benmanes.caffeine.cache.CacheWriter;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.RemovalCause;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching //开启缓存
public class CaffeineConfig {
    public static final int DEFAULT_MAXSIZE = 10000;
    public static final int DEFAULT_TTL = 600;

    /**
     * 定义cache名称、超时时长（秒）、最大容量
     * 每个cache缺省：10秒超时、最多缓存50000条数据，需要修改可以在构造方法的参数中指定。
     */
    public enum Caches {
        getUserById(600),          //有效期600秒
        listCustomers(7200, 1000),  //有效期2个小时 , 最大容量1000
        ;

        Caches() {
        }

        Caches(int ttl) {
            this.ttl = ttl;
        }

        Caches(int ttl, int maxSize) {
            this.ttl = ttl;
            this.maxSize = maxSize;
        }

        private int maxSize = DEFAULT_MAXSIZE;    //最大數量
        private int ttl = DEFAULT_TTL;        //过期时间（秒）

        public int getMaxSize() {
            return maxSize;
        }

        public int getTtl() {
            return ttl;
        }
    }

    /**
     * 创建基于Caffeine的Cache Manager
     *
     * @return
     */
    @Bean
    @Primary
    public CacheManager caffeineCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        ArrayList<CaffeineCache> caches = new ArrayList<CaffeineCache>();
        for (Caches c : Caches.values()) {
            caches.add(new CaffeineCache(c.name(),
                    Caffeine.newBuilder().recordStats()
                            .expireAfterWrite(c.getTtl(), TimeUnit.SECONDS)
                            .maximumSize(c.getMaxSize())
                            .build())
            );
        }
        cacheManager.setCaches(caches);
        return cacheManager;
    }

    @Bean
    public LoadingCache expiryCache() {
        LoadingCache<String, Object> loadingCache = Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)
                //缓存写入/删除监控
                .writer(new CacheWriter<Object, Object>() {
                    @Override
                    public void write(Object key, Object value) { //此方法是同步阻塞的
                        System.out.println("--缓存写入--:key=" + key + ", value=" + value);
                    }

                    @Override
                    public void delete(Object key, Object value, RemovalCause cause) {
                        System.out.println("--缓存删除--:key=" + key);
                        System.out.println("--缓存删除--:cause=" + cause);
                    }
                })
                //半个小时不操作页面 清空缓存
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .build((String key) -> null);
        return loadingCache;
    }
}