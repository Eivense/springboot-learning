package eivense.springboot.learning.controller;

import eivense.springboot.learning.service.TestConcurrentService;
import eivense.springboot.learning.service.ThreadPoolServiceGuavaImpl;
import eivense.springboot.learning.service.ThreadPoolServiceJdkImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Eivense
 * @date 2021/6/6 5:25 下午
 */
@RestController
public class TestController implements BaseController {

    @Autowired
    private ThreadPoolServiceGuavaImpl threadPoolService;

    @Autowired
    private ThreadPoolServiceJdkImpl threadPoolServiceJdk;

    @Autowired
    private TestConcurrentService testConcurrentService;

    @GetMapping("/test")
    public void test() {
        testConcurrentService.prcoess();
    }
}
