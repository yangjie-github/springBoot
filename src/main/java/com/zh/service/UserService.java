package com.zh.service;

import com.zh.entity.User;
import com.zh.mapper.UserMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author yangjie
 * 2019/4/2 7:22
 */

@CacheConfig( //指定统一的缓存名称，抽取缓存的公共配置
        cacheNames = "user",
        cacheManager = "userRedisCacheManager" //
//        keyGenerator = "",

)
@Service

public class UserService {

    @Autowired
    private UserMapper userMapper;

    //在调用方法之前，查看有没有缓存数据，如果有就返回缓存，没有查询之后然后做缓存；
    //将方法的运行结果进行缓存，以后有形同的数据，只需要查缓存，不需要调用方法
    //CacheManager管理多个Cache组件，对缓存的整整CRUD操作在Cache组建中，每一个缓存有自己唯一一个名字；
    //属性：cacheNames/value，指定缓存的名字,数组，将方法的返回结果放在哪个缓存中；
    //     key,缓存数据使用的key，默认是使用方法的参数； 缓存是： id - 返回值
    //     key的格式：使用spel, #id, 参数id的值， 等同于 #a0, #p0, #root.args[0]
    //     keyGenerator，key的生成器，可以指定key的生成器；
    //     key和keyGenerator二选一,自己制定key的生成器：
    //     cacheManager缓存管理器，cacheResolver缓存解析器，这两个二选一
    //     condition指定符合条件的情况下才缓存
    //     unless否定缓存（除非）， 和condition相反
    //     sync: 是都是异步模式；
//    @Cacheable(cacheNames = {"user"}, key = "#root.methodName + '[' + #id + ']'", condition = "#id > 0")
//    @Cacheable(cacheNames = {"user"}, keyGenerator = "yangJieGenerator")
    @Cacheable(key = "#id", condition = "#id > 0")
    public User getUserById(Integer id) {
        System.out.println("getUser...查询了");
        return userMapper.getUserById(id);
    }

    public User insertUser(User user) {
        userMapper.insertUser(user);
        return user;
    }

    /**
     * CachePut 是既调用方法，又更新缓存数据。目的：同步更新缓存，但是取和放缓存的key必须一致；
     * 使用场景，修改了数据库的某个数据， 同时更新缓存
     * 直接先掉方法，运行完成之后写入缓存；
     * 测试：
     *      1、先查询一个员工，结果放在缓存中
     *      2、更新这个员工，使用CachePut
     *      3、再查询这个员工，员工信息还是更新前的信息？ 由于更新和查询的key不一致。需要统一指定key
     *          修改更新方法的key, #result.id/#user.id 注意Cacheable的key不能使用#result.id，因为他是先查询，一开始的时候没有result
     */
    @CachePut(key = "#result.id")
    public User updateUser(User user) {
        userMapper.updateUser(user);
        return user;
    }

    /**
     * CacheEvict,缓存清除
     * 通过key来指定要清除的数据
     * allEntries = true 是否删除 user 里面的所有缓存数据
     * beforeInvocation = true 缓存的执行是否在方法之前执行；（默认是在方法执行之后执行；如果执行删除时出错，则缓存不会清空）
     */
    @CacheEvict(allEntries = true)
    public void deleteUserById(Integer id) {
        System.out.println("删除一个员工");
        /*userMapper.deleteUserById(id);*/
    }

    /**
     * Caching缓存的集合使用,组合注解，定义复杂的缓存规则
     */
    @Caching(
            cacheable = {
                    @Cacheable(key = "#name")
            },
            put = {
                    @CachePut(key = "#result.id"),
                    @CachePut(key = "#result.name")
            }
    )
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    /**
     * 监听来自消息队列里面user相关的内容
     * queues需要监听的队列， 可以是数组
     */
    @RabbitListener(queues = "yangjie.news")
    public void receive(User user) {
        System.out.println(user);
    }

    /**
     * 获取消息的头信息Message
     * @param message
     */
    @RabbitListener(queues = "yangjie")
    public void receiveMessage(Message message) {
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
