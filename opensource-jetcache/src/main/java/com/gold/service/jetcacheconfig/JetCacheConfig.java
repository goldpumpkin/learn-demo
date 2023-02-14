package com.gold.service.jetcacheconfig;


import com.alicp.jetcache.anno.CacheConsts;
import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.alicp.jetcache.anno.support.GlobalCacheConfig;
import com.alicp.jetcache.anno.support.SpringConfigProvider;
import com.alicp.jetcache.embedded.EmbeddedCacheBuilder;
import com.alicp.jetcache.embedded.LinkedHashMapCacheBuilder;
import com.alicp.jetcache.redis.RedisCacheBuilder;
import com.alicp.jetcache.support.FastjsonKeyConvertor;
import com.alicp.jetcache.support.JavaValueDecoder;
import com.alicp.jetcache.support.JavaValueEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Jetcahce 配置类
 * @Author: GoldHuang
 */
@Configuration
@EnableMethodCache(basePackages = "com.gold")
@EnableCreateCacheAnnotation
public class JetCacheConfig {

    private static final String KEY_PREFIX = "jetcache";

    @Autowired
    private ApplicationContext appContext;

    /**
     * 获取redis连接池
     * @return
     * @throws Exception
     */
    @Bean
    public Pool<Jedis> pool() throws Exception{
        RedisTemplate redisTemplate = (RedisTemplate) appContext.getBean("redisTemplate");
        JedisConnection jedisConnection = (JedisConnection)redisTemplate.getConnectionFactory().getConnection();
        Field field =  JedisConnection.class.getDeclaredField("pool");
        field.setAccessible(true);
        Pool<Jedis> jedisPool = (Pool<Jedis>) field.get(jedisConnection);
        return jedisPool;
    }

    @Bean
    public SpringConfigProvider springConfigProvider() {
        return new SpringConfigProvider();
    }

    @Bean
    public GlobalCacheConfig config(SpringConfigProvider configProvider, Pool<Jedis> pool){
        // Local Cache Config
        Map localBuilders = new HashMap();
        EmbeddedCacheBuilder localBuilder = LinkedHashMapCacheBuilder
                .createLinkedHashMapCacheBuilder()
                .keyConvertor(FastjsonKeyConvertor.INSTANCE);
        localBuilders.put(CacheConsts.DEFAULT_AREA, localBuilder);

        // Remote Cache Config
        Map remoteBuilders = new HashMap();
        RedisCacheBuilder remoteCacheBuilder = RedisCacheBuilder.createRedisCacheBuilder()
                .keyConvertor(FastjsonKeyConvertor.INSTANCE)
                .valueEncoder(JavaValueEncoder.INSTANCE)
                .valueDecoder(JavaValueDecoder.INSTANCE)
                .keyPrefix(KEY_PREFIX + ":")
                .jedisPool(pool);
        remoteBuilders.put(CacheConsts.DEFAULT_AREA, remoteCacheBuilder);

        GlobalCacheConfig globalCacheConfig = new GlobalCacheConfig();
        // log打印统计时间设置，单位min
        globalCacheConfig.setStatIntervalMinutes(60);
        globalCacheConfig.setConfigProvider(configProvider);
        globalCacheConfig.setLocalCacheBuilders(localBuilders);
        globalCacheConfig.setRemoteCacheBuilders(remoteBuilders);
        globalCacheConfig.setAreaInCacheName(false);

        return globalCacheConfig;
    }
}
