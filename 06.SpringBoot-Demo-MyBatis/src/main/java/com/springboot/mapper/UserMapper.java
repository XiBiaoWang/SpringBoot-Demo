package com.springboot.mapper;

import com.springboot.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname UserMapper
 * @Description TODO
 */

@Repository
public interface UserMapper {
    List<User> selectAll();
}
