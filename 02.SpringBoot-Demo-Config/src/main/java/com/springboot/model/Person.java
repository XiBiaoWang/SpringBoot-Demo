package com.springboot.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Classname Person
 * @Description TODO
 * @Date 2020/2/5 23:32
 * @Created by 86135
 */
@Component
@ConfigurationProperties(prefix="person")
public class Person {
    private String name;
    private Integer age;
    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public String toString(){
        return this.name + ";" + this.age + ";" +this.address;
    }
}
