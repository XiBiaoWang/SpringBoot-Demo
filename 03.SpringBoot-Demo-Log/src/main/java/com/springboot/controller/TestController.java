package com.springboot.controller;

import com.springboot.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname TestController
 * @Description TODO
 */
@RestController
@RequestMapping("/testController")
public class TestController {
    @Autowired
    LogService logService;

    @RequestMapping("/test")
    public String test(){
        return logService.logTest();
    }
}
