package com.reda.concurrent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author reda
 * @date 7/13/18 2:54 PM
 *
 * result:
 * Exception in thread "main" java.util.ConcurrentModificationException
at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:901)
at java.util.ArrayList$Itr.next(ArrayList.java:851)
at com.reda.concurrent.ModifyExceptionTest.main(ModifyExceptionTest.java:26)

 */
public class ModifyExceptionTest {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
        list.add(1);
        list.add(12);
        list.add(13);
        list.add(121);
        list.add(143);
        list.add(1254);
        new Thread(new Runnable() {
            @Override
            public void run() {
                list.remove(3);
            }
        }).start();

        for (Integer i:list) {
            System.out.println(i);
            Thread.sleep(100);
        }
    }
}
class Apple {
    int size;
    public void setSize(int size) {
        this.size = size;
    }
}
