package eivense.springboot.learning.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "test";
    }


    @RequestMapping("test1")
    public String test1() {
        return "test1";
    }

    @RequestMapping("test2")
    public String test2() {
        return "test2";
    }
}
