package com.reda.concurrent;

import com.reda.concurrent.part8.TimingThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author reda
 * @date 7/3/18 10:01 PM
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
//        ExecutorService threadPool = Executors.newCachedThreadPool();
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        TimingThreadPool threadPool = new TimingThreadPool(5,5,10, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
        for (int i =0; i < 6; i++) {
            threadPool.execute(new MyThread());
        }
        threadPool.shutdown();
    }
}

class MyThread implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < 10 ;i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
