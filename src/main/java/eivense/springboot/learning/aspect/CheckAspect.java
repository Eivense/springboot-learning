package eivense.springboot.learning.aspect;


import eivense.springboot.learning.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component("CheckAspect")
@Aspect
@Slf4j
public class CheckAspect {


    // 使用了@Controller和@RestController注解的类中的所有方法
    @Pointcut("@within(org.springframework.stereotype.Controller)||@within(org.springframework.web.bind.annotation.RestController)")
    public void controller(){}


    @Around("controller()")
    public Object verifyHeader(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = RequestUtil.getCurrentRequest();
        String ipAddr = RequestUtil.getRemoteHost(request);
        String url = request.getRequestURL().toString();
        log.info("Request IP: {} ,Request URL: {}",ipAddr,url);
        log.info("Header: {}", request.getHeaderNames());
        Object result = proceedingJoinPoint.proceed();
        return result;
    }

}
