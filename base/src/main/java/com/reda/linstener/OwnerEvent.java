package com.reda.linstener;

import java.util.EventObject;

public class OwnerEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public OwnerEvent(Object source) {
        super(source);
    }

    public void doSomeThing() {
        System.out.println("this is ownerEvent.");
    }

    public String getName() {
        return "event";
    }
}
