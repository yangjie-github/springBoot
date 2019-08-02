package com.zh.controller;

import com.zh.entity.User;
import com.zh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjie
 * 2019/3/31 11:05
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/insertUser")
    public User insertUser(User user) {
        userService.insertUser(user);
        return user;
    }

    @GetMapping("/updateUser")
    public User updateUser(User user) {
        userService.updateUser(user);
        return user;
    }

    @GetMapping("/deleteUserById/{id}")
    public void deleteUserById(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/getUserByName/{name}")
    public void getUserByName(@PathVariable("name") String name) {
        userService.getUserByName(name);
    }
}
