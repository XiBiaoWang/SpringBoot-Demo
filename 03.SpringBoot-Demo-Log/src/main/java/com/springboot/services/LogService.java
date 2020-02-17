package com.springboot.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Classname LogService
 * @Description TODO
 */
@Service
public class LogService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    public String logTest(){
        log.debug("log-service debug");
        log.info("log-service info");
        log.warn("log-service warn");
        log.error("log-service error");
        return "log service test";
    }
}
