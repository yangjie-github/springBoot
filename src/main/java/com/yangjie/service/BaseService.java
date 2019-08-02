package com.yangjie.service;

import com.yangjie.entity.Base;
import com.yangjie.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

/**
 * @author yangjie
 * 2019/4/2 15:45
 */
@CacheConfig( //指定统一的缓存名称，抽取缓存的公共配置
        cacheNames = "base",
        cacheManager = "baseRedisCacheManager" //

)
@Service
public class BaseService {

    @Autowired
    private BaseMapper baseMapper;

    @Qualifier("baseRedisCacheManager")//根据方法名明确获取缓存管理器
    @Autowired
    private RedisCacheManager baseRedisCacheManager;

    //可以将json缓存到redis,但是取得时候会出错，不能反序列化回来，因为cachaManege是使用RedisTemplate<Object, User> redisTemplate来序列化数据的
    //解决办法：再添加一个RedisTemplate<Object, Base> redisTemplate
    @Cacheable
    public Base getBaseById(Integer id) {
        return baseMapper.getBaseById(id);
    }

    //使用编码的方式缓存数据
    public Base findBaseById(Integer id) {
        Cache base = baseRedisCacheManager.getCache("base");
        Base baseById = baseMapper.getBaseById(id);
        base.put("base:2", baseById);
        return baseMapper.getBaseById(id);
    }
}
