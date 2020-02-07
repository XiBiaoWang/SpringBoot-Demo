package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Classname TestController
 * @Description TODO
 * @Date 2020/2/3 16:08
 * @Created by 86135
 */
@Controller
@RequestMapping("/testController")

public class TestController {
    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "hello test";
    }
}
