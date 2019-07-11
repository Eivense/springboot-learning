package eivense.springboot.learning.proxy;

public interface TestTarget {

    @ProxyMethod
    public void A();


    public void B();
}
