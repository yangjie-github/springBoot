package com.yangjie.config.springboot;

import com.yangjie.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangjie
 * @date 2019/3/16 15:11
 *
 * Configuration告诉spingboot当前类是一个配置类，相当于之前的配置类xml， 替代之前的配置类
 */

@Configuration
public class YangJieSpringBootConfig {

    //将方法的返回值添加到容器中， 默认id是方法名
    @Bean
    public HelloService helloService() {
        return new HelloService();
    }
}
