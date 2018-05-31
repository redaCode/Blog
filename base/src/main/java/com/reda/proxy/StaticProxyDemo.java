package com.reda.proxy;

import com.reda.proxy.itfs.Move;

//静态代理demo
public class StaticProxyDemo implements Move{

    Move move;

    StaticProxyDemo(Move move) {
        this.move = move;
    }
    @Override
    public void move() {
        System.out.println("static move");
        move.move();
    }

    public static void main(String[] args) {
        Move move = new StaticProxyDemo(new Foot());
        move.move();
    }
}


