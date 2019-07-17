package eivense.springboot.learning.proxy;


import org.springframework.stereotype.Component;

@Component
public class TestTargetImpl implements TestTarget{

    @Override
    public void A() {
        System.out.println("a");
    }

    @Override
    public void B() {
        System.out.println("b");
    }
}
