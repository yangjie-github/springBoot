package com.yangjie.mapper;

import com.yangjie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yangjie
 * 2019/3/31 21:19
 */

//配置版
// 配置sql文件，mybatis/mapper/EmployeeMapper.xml
//配置注解版的全局配置文件mybatis/mybatis-config.xml
@Mapper
public interface EmployeeMapper {

    public Employee getEmp(Integer id);

    public void insertEmp(Employee employee);
}
