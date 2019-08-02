package com.yangjie.mapper;

import com.yangjie.entity.User;
import org.apache.ibatis.annotations.*;

/**
 * @author yangjie
 * 2019/3/31 10:58
 */

//注解版
//注解版配置驼峰命名YangJieMybatisConfig
//Mapper指定这是一个操作数据库的mapper
@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    public User getUserById(Integer id);

    @Delete("delete from user where id = #{id}")
    public int deleteUserById(Integer id);

    //返回生成的主键
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user(name) values (#{name})")
    public int insertUser(User user);

    @Update("update user set name = #{name} where id = #{id}")
    public int updateUser(User user);

    @Select("select * from user where name = #{name}")
    User getUserByName(String name);
}
