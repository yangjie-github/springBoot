package com.yangjie;

import com.yangjie.entity.User;
import com.yangjie.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yangjie
 * 2019/4/2 8:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    //k-v专门抽取出一个操作字符串的
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    //k-v是对象
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisTemplate<Object, User> userRedisTemplate;

    @Autowired
    private UserService userService;

    @Test
    public void test01() {
        stringRedisTemplate.opsForValue().append("hello", "world");
    }

    //测试保存对象
    @Test
    public void testObject() {
        //默认保存对象，会使用jdk的序列化机制，序列化后的数据保存中redis
//        redisTemplate.opsForValue().set("user01", userService.getUserById(1));
        //将数据以json数据保存
        //1.自己将数据转换为json,自己实现
        //2.改变redisTemplate默认序列化规则：com.yangjie.config.chche.YangJieRedisConfig
        userRedisTemplate.opsForValue().set("user01", userService.getUserById(1));
    }
}
