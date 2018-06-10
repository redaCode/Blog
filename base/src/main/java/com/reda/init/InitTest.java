package com.reda.init;

public class InitTest {
    public static void main(String[] args) {
        Shape shape = new Ellipse();
    }
}
class Shape {

    int size = 5;

    Shape() {
        System.out.println(size);
        print();
    }
    public void print() {
        System.out.println(size);
    }
}
class Ellipse extends Shape{
    int size = 6;

    static {
        System.out.println("I am static method.");
    }
    Ellipse() {
        System.out.println(size);
        print();
    }
    public void print() {
        System.out.println(size);
    }
}