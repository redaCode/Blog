package com.reda.reflect;

public class ReflectDemo {
    private String privateFiled = "private filed qaq";

    public String publicFiled = "public filed qaq";

    boolean BoolFiled = true;

    private void privateMethod() {
        System.out.println("private method");
    }

    public void publicMethod(String str) {
        System.out.println("public method " +str);
    }

    public void publicMethodWithoutParam() {
        System.out.println("public method without param");
    }

    public ReflectDemo() {}

    public ReflectDemo(String str){};

    private ReflectDemo(String str1,String str2){};
}
