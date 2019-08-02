package com.yangjie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author yangjie
 * 2019/3/26 21:22
 */
@Controller
public class LoginController {

    @PostMapping(value = "/user/login")
    public String login(@RequestParam String userName,
                        @RequestParam String password,
                        Map<String, String> map,
                        HttpSession httpSession) {

        if (!StringUtils.isEmpty(userName) && "123".equals(password)) {
            httpSession.setAttribute("loginUser", userName);
            //防止表单重复提交，可以重定向
            return "redirect:/success";
        } else {
            map.put("msg", "用户名密码错误");
            return "login";
        }
    }
}
