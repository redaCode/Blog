package com.reda.concurrent;

/**
 * 模拟父类的this引用逸出，创建子类对象会先调用父类的构造方法，构造方法里的
 * 实例方法如果被子类重写则会调用到子类的方法
 * @author reda
 * @date 7/7/18 2:26 PM
 */
public class EscapeTest1 {
    public static void main(String[] args) {
        EscapeSon f = new EscapeSon();
    }
}

class EscapeF {
    int x;

    EscapeF () {
        this.setX(5);
    }

    public void setX(int x) {
        this.x = x;
    }
}

class EscapeSon extends EscapeF {

    EscapeSon() {
        System.out.println("Son Constructor");
    }
    @Override
    public void setX(int x) {
        System.out.println("son method");
        this.x = x;
    }
}