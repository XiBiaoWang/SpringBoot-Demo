package com.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname TestController
 * @Description TODO
 */
@RestController
@RequestMapping("/testController")
public class TestController {
    @GetMapping("/test")
    public String test(){
        int i = 1/0;
        return "test";
    }
@RequestMapping("/test1")
public String test1(){
    try{
        int i = 1/0;
    }catch(Exception e){
        return "error:" + e.toString();
    }
    return "test1";
}
}
