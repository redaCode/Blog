package com.reda.concurrent.part4;

/**
 * @author reda
 * @date 7/12/18 10:35 AM
 */
public class MutablePoint {
    public int x,y;
    public MutablePoint() {
        x = 0;
        y = 0;
    }
    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
    public MutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" +x+","+y+")";
    }
}
