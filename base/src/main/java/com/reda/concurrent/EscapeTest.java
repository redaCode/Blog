package com.reda.concurrent;

/**
 * 模拟this引用逃逸
 * @author reda
 * @date 7/6/18 11:00 PM
 */
public class EscapeTest {
    int x;
    Thread t;
    EscapeTest() {
        //r如果在构造方法内启动线程，this引用会逸出，可能打印0
         t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("internal thread: "+x);
            }
        });
         this.setX(7);
        try {
            Thread.sleep(1000);
            System.out.println("init end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EscapeTest e = new EscapeTest();
        e.t.start();
        System.out.println("main thread: " +e.x);
    }

    //构造方法里调用了
    public void setX(int x) {
        this.x = x;
    }
}
