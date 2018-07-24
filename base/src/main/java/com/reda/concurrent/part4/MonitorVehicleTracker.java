package com.reda.concurrent.part4;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author reda
 * @date 7/12/18 10:34 AM
 */
public class MonitorVehicleTracker {
    private final Map<String,MutablePoint> locations;

    public MonitorVehicleTracker(Map<String,MutablePoint> locations) {
        this.locations = locations;
    }

    public synchronized Map<String,MutablePoint> getLocations() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint point = locations.get(id);
        return point == null ? null : new MutablePoint(point);
    }

    public synchronized void setLocation(String id, int x, int y) {
        MutablePoint point = locations.get(id);
        if (point != null) {
            point.x = x;
            point.y = y;
        }
    }


    private static Map<String,MutablePoint> deepCopy(Map<String,MutablePoint> map) {
        Map<String,MutablePoint> result = new HashMap<>();
        for (String id:map.keySet()
             ) {
            result.put(id,new MutablePoint(map.get(id)));
        }
        return Collections.unmodifiableMap(result);
    }
}
