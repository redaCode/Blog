package com.reda.concurrent.part5;

import java.util.concurrent.CountDownLatch;

/**
 * @author reda
 * @date 7/20/18 7:00 PM
 */
public class TestHarness {
    public static void main(String[] args) {
        TestHarness harness = new TestHarness();
        try {
            long result = harness.timeTasks(10, new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public long timeTasks(int nThreads,final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i =0; i< nThreads; i++) {
            Thread t= new Thread() {
                public void run() {
                    try{
                        startGate.await();
                        try {
                            task.run();
                        }finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }
        long start= System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }
}
