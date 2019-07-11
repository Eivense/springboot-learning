package eivense.springboot.learning.proxy;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Proxy;

public class TestClient {


    public static void main(String[] args) {

        TestTarget target = (TestTarget) Proxy.newProxyInstance(
                TestTarget.class.getClassLoader(),
                new Class[]{TestTarget.class},
                new TestProxy(new TestTargetImpl())
        );

        target.A();
        target.B();
    }
}
