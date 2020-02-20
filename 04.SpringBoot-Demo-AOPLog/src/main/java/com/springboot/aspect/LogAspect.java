package com.springboot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Classname LogAspect
 * @Description TODO
 */
@Aspect
@Component
public class LogAspect {
    private Logger log = LoggerFactory.getLogger(LogAspect.class);

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 定义切点，controller包下面的所以类的公有方法
     */
    @Pointcut(value = "execution(public * com.springboot.controller..*.*(..)))")
    public void requestLog(){}
    @Before("requestLog()")
    public void doBefore(JoinPoint joinPoint){
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes servletRequest = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequest.getRequest();
        log.info("RequestMapping:[{}]", request.getRequestURI());
        log.info("Param:[{}]", Arrays.toString(joinPoint.getArgs()));
    }
    @AfterReturning(returning = "response",pointcut = "requestLog()")
    public void doAfterReturning(Object response){
        log.info("response:[{}]",response);
        log.info("times:[{}]",System.currentTimeMillis() - startTime.get());
    }
}
