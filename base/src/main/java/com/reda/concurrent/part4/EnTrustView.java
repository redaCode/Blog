package com.reda.concurrent.part4;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author reda
 * @date 7/12/18 11:43 AM
 */
public class EnTrustView {
    public static void main(String[] args) {
        ConcurrentHashMap<String,Point> input = new ConcurrentHashMap<>();
        input.put("shg",new Point(1,0));
        input.put("zb",new Point(1,1));
        input.put("bz",new Point(1,2));
        EnTrustMonitorVehicleTracker tracker = new EnTrustMonitorVehicleTracker(input);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                tracker.setLocation("zb",4,9);
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Map.Entry<String,Point> item: tracker.getLocations().entrySet()) {
            System.out.println("name: " +item.getKey() + " value: " + item.getValue());
        }
    }
}
