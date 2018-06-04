package com.reda.advisor;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Func {

    @Pointcut("execution(void method1())")
    public void method1Exec() {}

    @Before("method1Exec()")
    public void beforeMethod1Exec() {
        System.out.println("before method1");
    }

    @After("execution(void method2())")
    public void afterMethod2Exec() {
        System.out.println("after method2");
    }
}
