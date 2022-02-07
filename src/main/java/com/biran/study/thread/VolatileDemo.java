package com.biran.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData {
    volatile int number = 0;

    AtomicInteger atomicInteger = new AtomicInteger();

    //int number = 0;
    public void addTo10() {
        this.number = 10;
    }

    public void addOne() {
        this.number++;
    }

    public void atomicAddOne() {
        atomicInteger.getAndIncrement();
    }
}

public class VolatileDemo {
    public static void main(String[] args) {
        nonAtomicExample();
        //visibilityByVolatile();
    }

    private static void nonAtomicExample() {
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for(int j = 1; j <= 1000; j++) {
                    myData.addOne();
                    myData.atomicAddOne();
                }
            }, String.valueOf(i)).start();
        }

        while(Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " finally number value:" + myData.number);
        System.out.println(Thread.currentThread().getName() + " finally atomicInteger value:" + myData.atomicInteger);
    }

    /**
     * volatile可见性验证
     */
    private static void visibilityByVolatile() {
        final MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo10();
            System.out.println(Thread.currentThread().getName() + " updated number is " + myData.number);
        }, "AAA").start();

        while (myData.number == 0) {

        }

        System.out.println(Thread.currentThread().getName() + " mission is over");
    }
}
