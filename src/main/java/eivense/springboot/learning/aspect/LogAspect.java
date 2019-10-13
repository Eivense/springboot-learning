package eivense.springboot.learning.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component("LogAspect")
@Slf4j
@Order(11)
public class LogAspect {

    @Pointcut("execution(* eivense.springboot.learning.controller.TestController.*(..))")
    public void controller(){}

    @Before("controller()")
    public void beforeMethod(JoinPoint jp){
        String methodName = jp.getSignature().getName();
        log.info("before the method {}",methodName);
    }


    @After("controller()")
    public void afterMethod(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        log.info("after the method {}",methodName);
    }


}
