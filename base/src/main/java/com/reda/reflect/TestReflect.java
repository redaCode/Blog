package com.reda.reflect;



import org.springframework.objenesis.ObjenesisHelper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestReflect {
    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("com.reda.reflect.ReflectDemo");
            //调用构造方法公有构造方法
            Constructor ctor1 = clazz.getConstructor();
            //带参数共有构造方法
            Constructor ctor2 = clazz.getConstructor(String.class);
            //私有构造方法
            Constructor ctor3 = clazz.getDeclaredConstructor(String.class,String.class);
            ctor3.setAccessible(true);

            Object obj = ctor1.newInstance();
            Object obj2 = ctor2.newInstance("qaq");
            Object obj3 = ctor3.newInstance("1","2");
            //调用公有域
            Field field1 = clazz.getField("publicFiled");
            System.out.println(field1.get(obj));
            //调用包可见域
            Field field2 = clazz.getDeclaredField("BoolFiled");
            System.out.println(field2.getBoolean(obj2));
            //调用私有域
            Field field3 = clazz.getDeclaredField("privateFiled");
            field3.setAccessible(true);
            System.out.println(field3.get(obj2));
            //调用方法public方法
            Method method1 = clazz.getMethod("publicMethodWithoutParam");
            method1.invoke(obj);
            //调用带参数的public方法
            Method method2 = clazz.getMethod("publicMethod",String.class);
            method2.invoke(obj3,"qaq");
            //调用私有方法
            Method privateMethod = clazz.getDeclaredMethod("privateMethod");
            privateMethod.setAccessible(true);
            privateMethod.invoke(obj3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
