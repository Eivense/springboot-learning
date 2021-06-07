package eivense.springboot.learning.controller;

import eivense.springboot.learning.service.ThreadPoolServiceGuavaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eivense
 * @date 2021/6/6 5:25 下午
 */
@RestController
public class TestController implements BaseController {

    @Autowired
    private ThreadPoolServiceGuavaImpl threadPoolService;

    @GetMapping("/test")
    public void test() {
        threadPoolService.testConcurrent();
    }
}
