package eivense.springboot.learning.proxy;


public class TestTargetImpl implements TestTarget{

    @Override
    public String A() {
        return "a";
    }

    @Override
    public String B() {
        return "b";
    }
}
