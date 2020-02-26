package com.springboot.pojo;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @Classname BaseBean
 * @Description TODO
 * @Date 2020/2/26 11:21
 * @Created by 86135
 */
public class BaseBean implements Serializable {
    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
