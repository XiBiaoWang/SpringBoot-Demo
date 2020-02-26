package com.springboot.service;

import com.springboot.mapper.UserMapper;
import com.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname UserService
 * @Description TODO
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUser(){
        return userMapper.selectAll();
    }
}
