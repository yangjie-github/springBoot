package com.yangjie.controller;

import com.yangjie.entity.Employee;
import com.yangjie.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjie
 * 2019/3/31 21:39
 */
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeMapper employeeMapper;

    @RequestMapping(value = "/getEmp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id) {
        return employeeMapper.getEmp(id);
    }

    @RequestMapping(value = "/insertEmp")
    public Employee insertEmp(Employee employee) {
         employeeMapper.insertEmp(employee);
         return employee;
    }
}
