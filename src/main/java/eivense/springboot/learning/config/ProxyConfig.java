package eivense.springboot.learning.config;


import eivense.springboot.learning.proxy.TestTarget;
import eivense.springboot.learning.proxy.TestTargetImpl1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyConfig {


//    @Bean
//    public String a(){
//        return "a";
//    }
//
//    @Bean(name="aa")
//    public String aa(){
//        return "b";
//    }

//    @Bean
////    @Scope(value = "request")
////    public TestTarget testTarget(String aa) {
////        switch (aa) {
////            default:
////            case "a":
////                return new TestTargetImpl();
////            case "b":
////                return new TestTargetImpl1();
////        }
////    }
    @Bean
    public TestTarget testTarget(){
        return new TestTargetImpl1();
    }



}
