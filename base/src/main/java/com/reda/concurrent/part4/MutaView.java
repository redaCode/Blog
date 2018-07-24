package com.reda.concurrent.part4;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author reda
 * @date 7/12/18 10:56 AM
 */
public class MutaView {
    public static void main(String[] args) {
        HashMap<String,MutablePoint> input = new HashMap<>();
        input.put("shg",new MutablePoint(1,0));
        input.put("zb",new MutablePoint(1,1));
        input.put("bz",new MutablePoint(1,2));
        MonitorVehicleTracker tracker = new MonitorVehicleTracker(input);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                tracker.setLocation("zb",4,9);
            }
        });
        for (Map.Entry<String,MutablePoint> item: tracker.getLocations().entrySet()) {
            System.out.println("name: " +item.getKey() + " value: " + item.getValue());
        }
    }

}

