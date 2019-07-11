package eivense.springboot.learning.proxy;



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
