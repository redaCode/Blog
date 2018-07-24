package com.reda.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author reda
 * @date 7/23/18 6:16 PM
 */
public class FutureTaskTest {
    public void test() {
        FutureTask<String>  res=  new FutureTask(new Callable<String>() {
            @Override
            public String call() {
                try {
                    System.out.println(Thread.currentThread().getName()+ " Running");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "123";
            }
        });

        new Thread(res).start();

        try {
            System.out.println(Thread.currentThread().getName()+ " " +res.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        FutureTaskTest test = new FutureTaskTest();
        test.test();
    }
}
