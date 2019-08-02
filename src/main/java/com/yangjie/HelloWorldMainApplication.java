package com.yangjie;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author yangjie
 * @date 2019/3/16 9:55
 *
 * SpringBootApplication来标准一个主程序类， 说明这是一个spring-boot应用
 * 注意：SpringBootApplication里面的AutoConfigurationPackage会扫描本类所在包及其下面所有子包里面的所有组件扫描到spring容器里
 * ImportResource导入spring的配置文件， 但是springboot推荐的方式是：添加一个配置类，而不是添加xml配置；
 *      添加一个配置类：（在zh.config下面，自己建一个配置类）
 *  MapperScan指定mapper配置文件的basepackage包，这样在mapper文件上可以不用每个都写mapper注解
 *
 *  ====================
 *
 *  @EnableCaching 缓存默认使用的是ConcurrentMapCacheManager == ConcurrentMapCache,将数据保留在ConcurrentMapObject(例如com.yangjie.service.HelloService)
 *  在开发中我们使用缓存中间件，redis、memcached、ehcache
 *
 *  ===================
 *  整合redis
 *  1.使用docker安装redis;
 *  2.引入redis的starter;
 *  3.配置redis,在配置文件中指定redis的主机地址
 *  4.引入redis之后，redisAutoConfiguration就起作用了
 *      1) 引入之后，容器中保存的是redisCacheManage创建redisCache作为缓存组件，通过redis缓存数据；
 *      2) k-v若是对象的话，则默认是序列化的形式存入到redis
 *      3) 自动转json：默认创建的redisCacheManege默认用的序列化机制是jdk;需要自定义cacheManege;
 *
 *  ======================
 *  rabbitautoconfiguration自动配置
 *  1.配置了连接工厂
 *  2.RabbiProperties封装了rabbitmq的配置
 *  3.rabbitTemplate给rabbit发送和接受消息
 *  4.AmqpAdmin rabbitmq系统管理组件，可以创建队列和交换器, Queue, Exchange, Binding
 *  5.监听消息队列里面的内容
 *
 */
//@ImportResource(locations = {"classpath:beans.xml"})
//@MapperScan(value = "com.yangjie.mapper")
//EnableRabbit开启给基于注解的rabbimq
@EnableRabbit
@EnableCaching
@SpringBootApplication
public class HelloWorldMainApplication {

    public static void main(String[] args) {

        //spring应用启动起来
        SpringApplication.run(HelloWorldMainApplication.class, args);
    }

}
