package com.yangjie;

import com.yangjie.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 可以在测试期间很方便的类似编码一样进行自动注入等容器的功能
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    //logger日志记录
    Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);

    @Autowired
    Person person;

    @Autowired
    ApplicationContext ioc;

    @Test
    public void contextLoads() {
        System.out.println(person);
    }

    @Test
    public void helloService() {
        final boolean helloService = ioc.containsBean("helloService");

        logger.trace("跟踪");
        logger.debug("debug");
        logger.info("输出");
        logger.warn("警告");
        logger.error("错误");
        System.out.println(helloService);
    }

}
