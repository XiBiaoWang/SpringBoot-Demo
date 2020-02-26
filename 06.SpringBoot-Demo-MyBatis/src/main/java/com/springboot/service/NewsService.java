package com.springboot.service;

import com.springboot.mapper.NewsMapper;
import com.springboot.mapper.UserMapper;
import com.springboot.pojo.News;
import com.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname UserService
 * @Description TODO
 */
@Service
public class NewsService {
    @Autowired
    private NewsMapper newsMapper;

    public List<News> getAllNews(){
        return newsMapper.selectAll();
    }

    public News getNewsById(Integer id){
        return newsMapper.selectById(id);
    }
}
