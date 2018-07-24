package com.reda.concurrent;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author reda
 * @date 7/3/18 10:27 PM
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<Integer> rs = exec.submit(new MyCallable());
        System.out.println(rs.isDone());
        System.out.println(rs.get() + " ");
    }
}

class MyCallable implements Callable<Integer> {

    Random random = new Random(47);
    @Override
    public Integer call() throws Exception {
//        Thread.sleep(5);
        return random.nextInt();
    }
}
