package eivense.springboot.learning.proxy;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
public class TestTargetImpl1 implements TestTarget {
    @Override
    public String A() {
        return "A";
    }

    @Override
    public String B() {
        return "B";
    }



    @PostConstruct
    public void before(){
        log.error("postconstruct");
    }


    @PreDestroy
    public void destroy(){
        log.error("destroy");
    }
}
