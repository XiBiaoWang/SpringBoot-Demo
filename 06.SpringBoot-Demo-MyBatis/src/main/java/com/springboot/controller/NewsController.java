package com.springboot.controller;

import com.springboot.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname NewsController
 * @Description TODO
 */
@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;
    @GetMapping("/getnews/{id}")
    public String getNews(@PathVariable("id") Integer id){
        return newsService.getNewsById(id).toString();
    }
}
