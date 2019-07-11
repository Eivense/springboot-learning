package eivense.springboot.learning.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TestProxy implements InvocationHandler {

    public Object target;

    public TestProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = null;

        for (Annotation annotation : method.getAnnotations()) {
            if(annotation.annotationType().equals(ProxyMethod.class)){
                System.out.println("----------before----------");
                result = method.invoke(target, args);
                System.out.println("----------after----------");
            }
        }
        return result;
    }
}
