package com.yangjie.repository;

import com.yangjie.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yangjie
 * 2019/3/31 22:19
 */
//JpaRepository<Staff, Integer>staff是实体类， integer是实体类的主键类型
public interface StaffRepository extends JpaRepository<Staff, Integer> {


}
