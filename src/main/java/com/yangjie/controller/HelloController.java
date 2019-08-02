package com.yangjie.controller;

import com.yangjie.config.exception.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author yangjie
 * @date 2019/3/16 10:06
 *
 * ResponseBody可以放在类上面，表示这个类所有的方法都是返回json
 * RestController是@Controller和ResponseBody的结合
 *
 */
@Controller
public class HelloController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("hello")
    public String hello(String user) {
        if (user.equals("aaa")) {
            //排除异常，回去自己定制的5xx.html页面；
            throw new UserNotExistException();
        }
        return "helloWorld";
    }

    @RequestMapping("success")
    public String success(Map<String, Object> map) {

        map.put("hello", "<h1>你好</h1>");

        //会去类路径下的静态资源的templates模板找success.html文件

        map.put("users", Arrays.asList("zhangsan", "lisi", "wangwu"));

        return "success";
    }

    //测试jdbcTemplate
    @RequestMapping("/jdbc")
    @ResponseBody
    public Map<String, Object> map() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from user");
        return maps.get(0);
    }


}
