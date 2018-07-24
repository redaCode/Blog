package com.reda.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author reda
 * @date 7/3/18 10:01 PM
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
//        ExecutorService threadPool = Executors.newCachedThreadPool();
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        for (int i =0; i < 6; i++) {
            threadPool.execute(new MyThread());
        }
    }
}

class MyThread implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i < 10 ;i++) {
            System.out.print(i + " ");
            Thread.yield();
        }
        System.out.println();
    }
}
