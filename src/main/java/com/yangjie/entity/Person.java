package com.yangjie.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yangjie
 * @date 2019/3/16 13:19
 * 将yml配置文件的值映射到person中
 * ConfigurationProperties是告诉springboot将本类中的所有属性和配置文件中的配置进行绑定
 * prefix = "person"经配置文件中的person属性进行一一映射
 * ConfigurationProperties需要引入配置文件的处理器
 * Component将该组件加载到容器中
 * Validated在赋值时候需要校验
 *
 * ConfigurationProperties和value赋值比较
 *                               ConfigurationProperties     value
 * 功能                           批量注入                     单个注入
 * 松散语法（驼峰命名）              支持                        不支持
 * SpEl（spring表达式）            不支持                       支持
 * JSR303数据校验                  支持                        支持
 * 复杂类型（比如person中的map）     支持                        不支持
 */
//指定加载指定文件的配置
//@PropertySource(value = {"classpath:person.properties"})
@Component
//默认从全局配置文件获取值
@ConfigurationProperties(prefix = "person")
@Validated
public class Person {

    /**
     * 这种也可以给属性赋值
     * ${person.name}取得是配置文件的值
     * #{11*5}spring表达式赋值
     * "true"直接赋值
     */
    @Length(min = 5)
//    @Value("${person.name}")
    private String name;

//    @Value("#{11*5}")
    private Integer age;

//    @Value("true")
    private Boolean boss;

    private Date birthday;

    private Map<String, Object> maps;

    private List<Object> lists;

    private Dog dog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birthday=" + birthday +
                ", maps=" + maps +
                ", lists=" + lists +
                ", dog=" + dog +
                '}';
    }
}
