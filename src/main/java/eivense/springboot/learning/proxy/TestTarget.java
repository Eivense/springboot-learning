package eivense.springboot.learning.proxy;

public interface TestTarget {

    @ProxyMethod
    public String A();


    @ProxyMethod
    public String B();
}
