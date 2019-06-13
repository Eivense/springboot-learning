package eivense.springboot.learning.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(LogAspect.class);
}
