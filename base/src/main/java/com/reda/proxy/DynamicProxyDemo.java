package com.reda.proxy;

import com.reda.proxy.itfs.Move;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//动态代理demo
public class DynamicProxyDemo {
    public static void main(String[] args) {
        Move move = (Move) Proxy.newProxyInstance(Move.class.getClassLoader(),new Class[]{Move.class},new ProxyHandler(new Foot()));
        move.move();
    }

}

class ProxyHandler implements InvocationHandler {

    Object obj;

    ProxyHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (proxy instanceof Move) {
            System.out.println("dynamic print");
            return method.invoke(obj,args);
        }
        return null;
    }
}

class Foot implements Move {
    public void move() {
        System.out.println("foot move");
    }
}
