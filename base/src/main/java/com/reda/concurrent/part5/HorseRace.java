package com.reda.concurrent.part5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author reda
 * @date 7/24/18 2:24 PM
 */
public class HorseRace {

    static final int FINISH_LINE = 75;
    private List<Horse> horses= new ArrayList<>();
    private ExecutorService exec = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;
    HorseRace(int nHorses,final int pause) {
        barrier = new CyclicBarrier(nHorses, new Runnable() {
            @Override
            public void run() {
                StringBuilder sb = new StringBuilder();
                for (int i =0;i<FINISH_LINE;i++)
                    sb.append("=");
                System.out.println(sb);
                for (Horse horse:horses
                     ) {
                    System.out.println(horse.tracks());
                }
                for (Horse horse:horses
                     ) {
                    if (horse.getStrides() >= FINISH_LINE) {
                        System.out.println(horse+ "win!");
                        exec.shutdownNow();
                        return;
                    }
                }
                try {
                    Thread.sleep(pause);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        for (int i = 0; i < nHorses; i++) {
            Horse horse = new Horse(barrier);
            horses.add(horse);
            exec.execute(horse);
        }
    }

    public static void main(String[] args) {
        int nHorse = 7;
        int pause = 200;
        new HorseRace(nHorse,pause);
    }
}

class Horse implements Runnable {

    private static int counter = 0;
    private final int id = counter++;
    private int strides =0;
    private static Random rand = new Random(47);
    public static CyclicBarrier barrier;
    Horse(CyclicBarrier b) {
        barrier = b;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    strides +=rand.nextInt(3);
                }
                barrier.await();
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
    public String toString() {
        return "Horse " + id + " ";
    }
    public String tracks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<getStrides();i++) {
            sb.append("*");
        }
        sb.append(id);
        return sb.toString();
    }

    public synchronized int getStrides() {
        return strides;
    }
}