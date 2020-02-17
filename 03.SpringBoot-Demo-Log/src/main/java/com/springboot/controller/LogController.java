package com.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname LogController
 * @Description TODO
 */
@RestController
@RequestMapping("/logController")
public class LogController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @RequestMapping("/logTest")
    public String logTest(){
        log.info("log-info");
        log.debug("log-debug");
        log.warn("log-warn");
        log.error("log-error");
        return "logtest";
    }
}
