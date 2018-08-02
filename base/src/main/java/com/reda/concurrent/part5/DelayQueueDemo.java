package com.reda.concurrent.part5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.*;

/**
 * @author reda
 * @date 7/24/18 3:52 PM
 */
public class DelayQueueDemo {
    public static void main(String[] args) {
        Random rand = new Random(47);
        ExecutorService exec = Executors.newCachedThreadPool();
        DelayQueue<DelayedTask> queue = new DelayQueue<>();
        for (int i =0; i< 20;i++) {
            queue.put(new DelayedTask(rand.nextInt(5000)));
        }
        queue.add(new DelayedTask.EndSentinel(5000,exec));
        exec.execute(new DelayedTaskConsumer(queue));
    }
}

class DelayedTask implements Runnable,Delayed {

    private static int counter = 0;
    private final int id = counter++;
    private final int delta;
    private final long trigger;

    protected static List<DelayedTask> sequence = new ArrayList<>();
    public DelayedTask(int delaySeconds) {
        delta = delaySeconds;
        trigger = System.nanoTime()+ NANOSECONDS.convert(delta,MILLISECONDS);
        sequence.add(this);

    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger-System.nanoTime(),NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayedTask that = (DelayedTask) o;
        if (trigger < that.trigger)
            return -1;
        if (trigger > that.trigger)
            return 1;
        return 0;
    }

    @Override
    public void run() {
        System.out.println(this + " ");
    }

    public String toString() {
        return String.format("[%1$-4d]",delta) + " Task "+ id;
    }

    public String summary() {
        return "(" + id + ":"+ delta+")";
    }

    public static class EndSentinel extends DelayedTask {
        private ExecutorService exec;
        public EndSentinel(int delay,ExecutorService e) {
            super(delay);
            exec =e;
        }
        public void run() {
            for (DelayedTask pt:sequence
                    ) {
                System.out.print(pt.summary() + " ");
            }
            System.out.println();
            System.out.println(this + " Calling shutdownNow()");
            exec.shutdownNow();
        }
    }
}

class DelayedTaskConsumer implements Runnable {

    private DelayQueue<DelayedTask> q;

    public DelayedTaskConsumer(DelayQueue q) {
        this.q = q;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted())
                q.take().run();
        }catch (InterruptedException e){}

        System.out.println("Finish DelayedTaskConsumer");
    }
}