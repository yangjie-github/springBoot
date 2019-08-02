package com.zh.controller;

import com.zh.entity.Staff;
import com.zh.repository.StaffRepository;
import com.zh.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjie
 * 2019/3/31 22:28
 */

//基于jpa的操作
@RestController
public class StaffController {

    @Autowired
    private StaffRepository staffRepository;

    @RequestMapping("/getStaff/{id}")
    public Staff getStaff(@PathVariable("id") Integer id) {
        Staff one = staffRepository.findOne(id);
        return one;
    }

    @RequestMapping("/insertStaff")
    public Staff insertStaff(Staff staff) {
        Staff save = staffRepository.save(staff);
        return save;
    }
}
