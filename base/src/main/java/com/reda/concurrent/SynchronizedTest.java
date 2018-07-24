package com.reda.concurrent;


/**
 * @author reda
 * @date 7/6/18 9:18 AM
 */
/**
 * method修饰非静态方法相当于对对象上锁，修饰静态方法，相当于对类上锁，持有类锁，不会影响对象锁方法的调用
 * 非同步方法的访问不受任何同步块方法的限制
 * @params
 * @return
 * @throws
*/
public class SynchronizedTest {
    public static void main(String[] args) throws InterruptedException {
        Weight weight = new Weight();
        Weight weight2 = new Weight();
//        new Thread(() -> Weight.print7()).start();
        Thread.sleep(100);
//        new Thread(() -> Weight.print8()).start();
        new Thread(() -> weight.print1()).start();
        new Thread(() -> weight.print2()).start();
//        for (int i =0; i< 5; i++) {
//            new Thread(() -> weight.print6()).start();
//        }

//        weight.print3();
    }

}

class Weight {
    public synchronized void print1() {
        System.out.println("before 1");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("print 1");
    }

    public void print2() {
        synchronized (this) {
            System.out.println("before 2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("print 2");
        }
    }

    public synchronized static void print4() {

        System.out.println("begore 4");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("4444.");
    }

    public synchronized static void print5() {

        System.out.println("begore 5");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("5555.");
    }

    public void print3() {
        System.out.println("I am not synchronized method.");
    }

    public void print6() {
        System.out.println("print 6");
        synchronized (this) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("end 6");
    }

    public static void print7() {
        System.out.println("print 7");
        synchronized (SynchronizedTest.class) {
            System.out.println("7777");

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("end 7");
    }
    public static void print8() {
        System.out.println("print 8");
        synchronized (SynchronizedTest.class) {
            System.out.println("888");

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("end 8");
    }
}
