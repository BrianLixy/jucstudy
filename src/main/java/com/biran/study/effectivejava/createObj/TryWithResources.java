package com.biran.study.effectivejava.createObj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TryWithResources {
    static String firstLineOfFile(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader br = new BufferedReader(fileReader);
        try {
            return  br.readLine();
        } finally {
            br.close();
        }
    }

    static String correctFirstLineOfFile(String path) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            return  br.readLine();
        }
    }

    public static void main(String[] args) {
        //Person p1 = new Person();

        System.out.println(Person.class);
        System.out.println("*******************");
        try {
            Class<?> c5 = Class.forName("biran.study.effectivejava.createObj.Person");
             c5.newInstance();
            System.out.println("11111111");
            System.out.println("classForname结束！");
            ClassLoader loader = Person.class.getClassLoader();
            Class<?> cl = loader.loadClass("com.biran.study.effectivejava.createObj.Person");
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
