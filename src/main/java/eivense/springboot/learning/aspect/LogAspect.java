package eivense.springboot.learning.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component("LogAspect")
@Slf4j
public class LogAspect {

//    private Logger logger = LoggerFactory.getLogger(LogAspect.class);


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


    @Around("controller()")
    public Object aroundMethod(ProceedingJoinPoint jp)throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        log.info("Request IP {} ,Request URL {} ,Request Method {} ",request.getRemoteAddr(),request.getRequestURL(),request.getMethod());
        return jp.proceed();
    }

}
