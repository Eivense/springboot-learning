package eivense.springboot.learning;

import eivense.springboot.learning.proxy.TestProxy;
import eivense.springboot.learning.proxy.TestTarget;
import eivense.springboot.learning.proxy.TestTargetImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Proxy;

@SpringBootApplication
public class LearningApplication {


    public static void main(String[] args) {
        SpringApplication.run(LearningApplication.class, args);

        ApplicationContext context = new AnnotationConfigApplicationContext();
        TestTargetImpl testTargetImpl = context.getBean(TestTargetImpl.class);
        TestTarget target = (TestTarget) Proxy.newProxyInstance(
                TestTarget.class.getClassLoader(),
                new Class[]{TestTarget.class},
                new TestProxy(testTargetImpl)
        );

        target.A();
        target.B();
    }

}
