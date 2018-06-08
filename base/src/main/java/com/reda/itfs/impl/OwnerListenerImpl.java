package com.reda.itfs.impl;

import com.reda.itfs.OwnListener;
import com.reda.linstener.OwnerEvent;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class OwnerListenerImpl implements OwnListener {
    @Override
    public void doBefore(OwnerEvent event) {
        System.out.println("do before " + event.getName());
    }

    @Override
    public void doAfter(OwnerEvent event) {
        System.out.println("do after " + event.getName());
    }
}
