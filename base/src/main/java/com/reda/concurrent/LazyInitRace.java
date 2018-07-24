package com.reda.concurrent;

/**
 * @author reda
 * @date 7/5/18 10:24 PM
 */

public class LazyInitRace {
    private LazyInitRace instance = null;

    public LazyInitRace getInstance() {
        if (instance == null) {
            instance = new LazyInitRace();
        }
        return instance;
    }

    private LazyInitRace(){};
}
