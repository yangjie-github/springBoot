package com.zh.mapper;

import com.zh.entity.Base;
import org.apache.ibatis.annotations.*;

/**
 * @author yangjie
 * 2019/4/2 15:31
 */
@Mapper
public interface BaseMapper {

    @Select("select * from base where id = #{id}")
    public Base getBaseById(Integer id);

    @Delete("delete from base where id = #{id}")
    public int deleteBaseById(Integer id);

    //返回生成的主键
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into base(name) values (#{name})")
    public int insertBase(Base base);

    @Update("update base set name = #{name} where id = #{id}")
    public int updateBase(Base base);

    @Select("select * from base where name = #{name}")
    Base getBaseByName(String name);
}
