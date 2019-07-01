package eivense.springboot.learning.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component("LogAspect")
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(LogAspect.class);


    @Before("execution(* eivense.springboot.learning.controller.TestController.*(..))")
    public void beforeMethod(JoinPoint jp){
        String methodName = jp.getSignature().getName();
        logger.info("before the method {}",methodName);
    }
}
