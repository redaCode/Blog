package com.reda.concurrent.part4;

/**
 * @author reda
 * @date 7/12/18 11:26 AM
 */
public class Point {
    public final int x;
    public final int y;

    public Point(int x,int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString() {
        return "(" +x+","+y+")";
    }

}
