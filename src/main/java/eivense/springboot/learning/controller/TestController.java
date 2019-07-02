package eivense.springboot.learning.controller;


import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "test";
    }


    @RequestMapping(value = "test2",method = RequestMethod.POST)
    public String test2(@RequestBody String body) {
        System.out.println(body);
        return "test2";
    }
}
