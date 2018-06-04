package com.reda;

import com.reda.entity.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@RestController
public class App 
{
    @Autowired
    Demo demo;

    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
    }

    /*
    * Aop拦截生效，打印结果为：
    * before method1
      exec method1
      exec method2
      after method2
    * */
    @RequestMapping("/test1")
    public void test1() {
        demo.method1();
        demo.method2();
    }

    /*没有触发AOP
    exec method1
    exec method2
    * */
    @RequestMapping("/test2")
    public void test2() {
        Demo internalDemo = new Demo();
        internalDemo.method1();
        internalDemo.method2();
    }
}
