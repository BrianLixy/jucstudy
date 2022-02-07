package com.biran.study.thread;

public class ResortSeqDemo {
    int a = 0;
    boolean flag = false;

    public void method1() {
        a = 1;
        flag = true;
    }

    public void method2() {
        if(flag) {
            a = a + 5;
            System.out.println("**********value = " + a);
        }
    }
}
