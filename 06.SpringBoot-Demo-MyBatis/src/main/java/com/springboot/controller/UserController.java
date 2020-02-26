package com.springboot.controller;

import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Classname UserController
 * @Description TODO
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/getAllUser")
    @ResponseBody
    public String getAllUser(){
        return userService.getAllUser().toString();
    }
}
