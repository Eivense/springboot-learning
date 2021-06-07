package eivense.springboot.learning.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component("LogAspect")
@Slf4j
@Order(11)
public class LogAspect {


    @Pointcut("target(org.springframework.web.bind.annotation.RestController)")
    public void baseController(){}


    @Before("baseController()")
    public void beforeMethod(JoinPoint jp){
        String methodName = jp.getSignature().getName();
        log.info("before the method {}",methodName);
    }


    @After("baseController()")
    public void afterMethod(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        log.info("after the method {}",methodName);
    }



}
