package com.biran.study.thread;

public class T1 {
    volatile int number = 0;

    public void addOne() {
        this.number++;
        new T1();
    }
}
