package com.yangjie;

import com.yangjie.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author yangjie
 * 2019/4/4 10:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbiMQTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private AmqpAdmin amqpAdmin;


    /**
     * 1. 单播（点对点）发送消息
     */
    @Test
    public void rabbit() {

        //Message需要自己构造一个，定义消息体的内容和消息头
        //rabbitTemplate.send(exchange, routKey, message);

        //object默认当为消息体，只需要传递哟啊发送的对象，自动序列化保存发送给rabbitmq,
        //修改序列化之后为json,添加配置YangJieAmqpConfig
        //rabbitTemplate.convertAndSend(exchange, routKey, object);

        HashMap<String, Object> map = new HashMap<>();
        map.put("message", "这是第一个消息");
        map.put("data", Arrays.asList("hellp", 123, true));
        //对象被默认序列化以后发送出去
        rabbitTemplate.convertAndSend("exchange.direct", "yangjie.news", map);
    }

    /**
     * 广播
     */
    @Test
    public void sendMsg() {
        rabbitTemplate.convertAndSend("exchange.fanout", "", new User(11, "yangjie"));
    }


    /**
     * 接受消息
     */
    @Test
    public void receive() {
        //接受到数据以后消息队列里面就没有了
        Object o = rabbitTemplate.receiveAndConvert("yangjie.news");
        System.out.println(o.getClass());
        System.out.println(o);

    }


    //==================================================================

    /**
     * 创建exchange
     */
    @Test
    public void createExchange() {

        DirectExchange liujia521 = new DirectExchange("liujia521");

        amqpAdmin.declareExchange(liujia521);
    }

    /**
     * 创建queue
     */
    @Test
    public void createQueue() {

        Queue queue = new Queue("liujia.core");

        amqpAdmin.declareQueue(queue);
    }

    /**
     * 创建绑定规则
     */
    @Test
    public void createBinding() {

        Binding binding = new Binding(
                "liujia.core",
                Binding.DestinationType.QUEUE,
                "liujia521",
                "liujia.#",
                null);

        amqpAdmin.declareBinding(binding);
    }

}
