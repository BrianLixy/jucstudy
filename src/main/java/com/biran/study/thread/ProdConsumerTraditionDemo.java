package com.biran.study.thread;

import javax.sound.midi.Track;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SharedData {
    private int number = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            // 1判断
            while(number != 0) {
                // 等待，不能生产
                condition.await();
            }
            // 2生产
            number++;
            System.out.println(Thread.currentThread().getName() + " " + number);
            // 3通知唤醒
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception {
        lock.lock();
        try {
            // 1判断
            while(number == 0) {
                // 等待，不能消费
                condition.await();
            }
            // 2消费
            number--;
            System.out.println(Thread.currentThread().getName() + " " + number);
            // 3通知唤醒
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 1 线程       操作（干活）     资源类
 * 2 判断       干活             通知
 * 3 防止虚假唤醒机制
 */
public class ProdConsumerTraditionDemo {
    public static void main(String[] args) {
        SharedData sharedData = new SharedData();

        new Thread(() -> {
            for(int i = 0; i < 5; i++) {
                try {
                    sharedData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for(int i = 0; i < 5; i++) {
                try {
                    sharedData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();

        new Thread(() -> {
            for(int i = 0; i < 5; i++) {
                try {
                    sharedData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();

        new Thread(() -> {
            for(int i = 0; i < 5; i++) {
                try {
                    sharedData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "DD").start();
    }
}
