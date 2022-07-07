package com.biran.study.effectivejava.createObj;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Person {
    //类变量
    private static String staticVal = "============》person static val";
    //实例成员变量
    private String val = "============》person val";
    //静态代码块
    static {
        System.out.println("============》person static block：" + staticVal);
    }
    //代码块
    {
        System.out.println("============》person block：" + val);
    }
    //构造方法
    public Person() {
        super();
        System.out.println("============》person Constructor：" + val);
    }
    //实例成员方法
    public void test() {
        System.out.println("============》person test method");
    }

    //静态方法
    public static void staticTest() {
        System.out.println("============》person static test method");
    }

    public static void main(String[] args) {
        //Person p1 = new Person();

        System.out.println(Person.class);
        System.out.println("*******************");
        try {
            Class<?> c5 = Class.forName("serviceproviderservice.Person");
            System.out.println("classForname结束！");
            ClassLoader loader = Person.class.getClassLoader();
            Class<?> cl = loader.loadClass("serviceproviderservice.Person");
            System.out.println("类装载结束！！！！");

            Method meStatic = cl.getMethod("staticTest", null);
            meStatic.invoke(cl, null);
            System.out.println("*********2**********");

            Class<?> aClass = c5.newInstance().getClass();
            Method me = aClass.getMethod("test", null);
            System.out.println(cl == aClass);
            me.invoke(c5.newInstance(), null);

            System.out.println("*********3**********");
            (new Person()).test();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
