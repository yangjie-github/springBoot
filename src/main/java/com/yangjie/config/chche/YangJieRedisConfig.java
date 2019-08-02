package com.yangjie.config.chche;

import com.yangjie.entity.Base;
import com.yangjie.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

/**
 * @author yangjie
 * 2019/4/2 8:46
 *
 * redis缓存管理器以json格式数据缓存
 */
@Configuration
public class YangJieRedisConfig {

    @Bean
    public RedisTemplate<Object, User> userRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {

        RedisTemplate<Object, User> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<User> serializer = new Jackson2JsonRedisSerializer<User>(User.class);
        redisTemplate.setDefaultSerializer(serializer);
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<Object, Base> baseRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {

        RedisTemplate<Object, Base> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Base> serializer = new Jackson2JsonRedisSerializer<Base>(Base.class);
        redisTemplate.setDefaultSerializer(serializer);
        return redisTemplate;
    }

    //创建redis的json缓存管理器
    @Primary//默认的，必须指定一个默认的，否则启动会报错
    @Bean
    public RedisCacheManager userRedisCacheManager(RedisTemplate<Object, User> redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        //key多了一个前缀， setUsePrefix会将cachename 作为key的前缀
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

    //创建redis的json缓存管理器
    @Bean
    public RedisCacheManager baseRedisCacheManager(RedisTemplate<Object, Base> redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        //key多了一个前缀， setUsePrefix会将cachename 作为key的前缀
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }
}
