package com.springboot.mapper;

import com.springboot.pojo.News;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Classname UserMapper
 * @Description TODO
 */

@Repository
public interface NewsMapper {
    List<News> selectAll();

    News selectById(Integer id);
}
