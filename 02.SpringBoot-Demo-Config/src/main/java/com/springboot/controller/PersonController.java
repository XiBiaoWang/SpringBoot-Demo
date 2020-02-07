package com.springboot.controller;

import com.springboot.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname PersonController
 * @Description TODO
 * @Date 2020/2/5 23:36
 * @Created by 86135
 */
@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private Person p;
    @RequestMapping("/getperson")
    public String getPerson(){
        return p.toString();
    }

}
