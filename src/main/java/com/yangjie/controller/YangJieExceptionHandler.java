package com.yangjie.controller;

import com.yangjie.config.exception.UserNotExistException;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangjie
 * @date 2019/3/27 19:15
 *
 */
//ControllerAdvice异常处理器
@ControllerAdvice
public class YangJieExceptionHandler {

    //UserNotExistException处理的异常类型
    //这样写了之后浏览器和客户端都是返回的是json数据
//    @ResponseBody
//    @ExceptionHandler({UserNotExistException.class})
//    public Map<String, Object> handlerException(Exception e) {
//
//        Map<String, Object> map = new HashMap<>();
//
//        map.put("code", "user.notExist");
//        map.put("message", e.getMessage());
//
//        return map;
//    }

    //扩展为自使用，浏览器返回页面，客户端返回json
    //出现错误之后，转发到/error，来实现自适应
    //这种不能带自定义的的错误信息；
    @ExceptionHandler({UserNotExistException.class})
    public String handlerException(Exception e, HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();
        //传入自己的错误状态码，不然都是200
        request.setAttribute("javax.servlet.error.status_code", 500);
        map.put("code", "user.notExist");
        map.put("message", e.getMessage());

        //在YangJieErrorAttribute中会用到；
        request.setAttribute("ext", map);

        //转发到error
        return "forward:/error";
    }


    //可以携带错输数据，对上一种的优化
    //1.完全来编写一个ErrorController的实现类， 放在容器中（太复杂）
    //页面上能用的数据和json返回的数据，defaultErrorAttribute默认进行数据处理,例如： YangJieErrorAttribute

}
