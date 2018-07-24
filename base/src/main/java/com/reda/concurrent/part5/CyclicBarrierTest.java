package com.reda.concurrent.part5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author reda
 * @date 7/23/18 6:30 PM
 */
public class CyclicBarrierTest {
    class MainTask implements Runnable {

        @Override
        public void run() {
            System.out.println("---main task work");
        }
    }

    class SubTask implements Runnable {

        private String name;
        private CyclicBarrier cyclicBarrier;

        SubTask(String name, CyclicBarrier cyclicBarrier) {
            this.name = name;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("---"+name + " task work");
            try {
                Thread.sleep(1000);
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrierTest cyclicBarrierTest = new CyclicBarrierTest();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2,cyclicBarrierTest.new MainTask());
        SubTask subTask1 = cyclicBarrierTest.new SubTask("A",cyclicBarrier);
        SubTask subTask2 = cyclicBarrierTest.new SubTask("B",cyclicBarrier);

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(subTask1);
        exec.execute(subTask2);
    }
}
