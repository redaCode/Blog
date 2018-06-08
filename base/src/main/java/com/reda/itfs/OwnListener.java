package com.reda.itfs;

import com.reda.linstener.OwnerEvent;

import java.util.EventListener;

public interface OwnListener extends EventListener {
    void doBefore(OwnerEvent event);

    void doAfter(OwnerEvent event);
}
