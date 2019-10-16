package eivense.springboot.learning.controller;


import eivense.springboot.learning.proxy.TestProxy;
import eivense.springboot.learning.proxy.TestTarget;
import eivense.springboot.learning.util.RedisUtil;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Proxy;

@RestController
@RequestMapping("/test")
public class TestController {


    @Autowired
    public BeanFactory beanFactory;

    @Autowired
    public RedisUtil redisUtil;

    @RequestMapping("1")
    public String test(){
        return "test";
    }


    @RequestMapping(value = "2",method = RequestMethod.POST)
    public String test2(@RequestBody String body) {
        System.out.println(body);
        return "test2";
    }

    @RequestMapping("3")
    public String test3(){

        TestTarget testTarget=beanFactory.getBean(TestTarget.class, "a");

        TestTarget target = (TestTarget) Proxy.newProxyInstance(
                TestTarget.class.getClassLoader(),
                new Class[]{TestTarget.class},
                new TestProxy(testTarget)
        );
        return target.A();
    }

    @RequestMapping("4")
    public String test4(){

        TestTarget testTarget=beanFactory.getBean(TestTarget.class, "b");

        TestTarget target = (TestTarget) Proxy.newProxyInstance(
                TestTarget.class.getClassLoader(),
                new Class[]{TestTarget.class},
                new TestProxy(testTarget)
        );


        return target.A();
    }


    @RequestMapping("set")
    public void testRedisSet(HttpServletRequest request){
        String key=request.getParameter("key");
        String value=request.getParameter("value");
        redisUtil.set(key,value);
    }

    @RequestMapping("get")
    public String testRedisGet(HttpServletRequest request){
        String key = request.getParameter("key");
        return redisUtil.get(key);
    }

}
