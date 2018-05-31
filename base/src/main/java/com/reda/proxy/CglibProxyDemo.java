package com.reda.proxy;

import com.reda.proxy.itfs.Move;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

//CGLIB动态字节码生成
public class CglibProxyDemo {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Foot.class);
        enhancer.setCallback(new MoveCallback());

        Move move = (Move) enhancer.create();
        move.move();
    }
}

class MoveCallback implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib proxy");
        return methodProxy.invokeSuper(o,args);
    }
}