package com.yangjie.config.chche;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author yangjie
 * 2019/4/2 6:56
 *
 * 自定义cache的KeyGenerator生成策略
 */
@Configuration
public class YangJieCacheConfig {

    @Bean("yangJieKeyGenerator")
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                return method.getName() + "[" + Arrays.asList(objects).toString() + "]";
            }
        };
    }
}
