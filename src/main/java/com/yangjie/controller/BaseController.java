package com.yangjie.controller;

import com.yangjie.entity.Base;
import com.yangjie.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjie
 * 2019/4/2 15:47
 */
@RestController
public class BaseController {

    @Autowired
    private BaseService baseService;

    @RequestMapping("/getBaseById/{id}")
    public Base getBaseById(@PathVariable("id") Integer id) {
        return baseService.getBaseById(id);
    }

    @RequestMapping("/getBaseId/{id}")
    public Base getBaseId(@PathVariable("id") Integer id) {
        return baseService.findBaseById(id);
    }
}
