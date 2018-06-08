package com.reda.linstener;

import com.reda.itfs.impl.OwnerListenerImpl;

import java.util.EventListener;

public class OwnerPublisher {
    private OwnerListenerImpl listener = new OwnerListenerImpl();

    public void publish() {
        OwnerEvent event = new OwnerEvent(new Object());
        listener.doBefore(event);
        event.doSomeThing();
        listener.doAfter(event);
    }

    public static void main(String[] args) {
        OwnerPublisher publisher = new OwnerPublisher();
        publisher.publish();
    }
}
