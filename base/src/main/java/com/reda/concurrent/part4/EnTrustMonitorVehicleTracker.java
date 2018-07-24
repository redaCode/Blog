package com.reda.concurrent.part4;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author reda
 * @date 7/12/18 10:34 AM
 */
public class EnTrustMonitorVehicleTracker {
    private final ConcurrentHashMap<String,Point> locations;
//    private final Map<String,Point> unModifiedmap;

    public EnTrustMonitorVehicleTracker(ConcurrentHashMap<String,Point> locations) {
        this.locations = locations;
//        this.unModifiedmap = Collections.unmodifiableMap(new HashMap<>(locations));
    }

    public Map<String,Point> getLocations() {
        return Collections.unmodifiableMap(new HashMap<>(locations));
    }

    public Point getLocation(String id) {
        Point point = locations.get(id);
        return point;
    }

    public void setLocation(String id, int x, int y) {
        locations.replace(id,new Point(x,y));
    }

}
