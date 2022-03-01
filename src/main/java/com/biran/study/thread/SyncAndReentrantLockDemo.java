package com.biran.study.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized 和 Lock有什么区别？用新的Lock有什么好处？
 * 1 原始构成
 *      synchronized是关键字属于JVM层面，
 *          monitorenter(底层通过monitor对象完成，wait/notify等方法也依赖于monitor对象，只有在同步块或方法中才能调用wait/notify等方法
 *          monitorexit
 *      Lock是具体类，是api层面的锁
 * 2使用方法
 *      synchronized 不需要用户去手动释放锁，当synchronized代码执行完后系统自动让线程释放对锁的使用
 *      ReentrantLock则需要用户手动释放锁，若没有主动释放，就有可能导致死锁
 *      需要lock/unlock方法配合try/finally语句块完成
 * 3 等待是否可中断
 *      synchronized不可中断，除非抛出异常或者正常运行完成
 *      ReentrantLock可中断， 1.设置超时方法tryLock(long timeout, TimeUnit unit)
 *                            2.lockInterruptibly()放在代码块中，调用interrupt()方法可中断
 * 4 加锁是否公平
 *  synchronized非公平锁
 *  ReentrantLock两者都可以，默认非公平
 * 5 锁绑定多个条件Condition
 *  synchronized没有
 *  ReentrantLock用来实现分组唤醒，需要唤醒可以精准唤醒，不像synchronized要么随机唤醒一个线程要么唤醒全部线程
 *
 *  =========================================
 *  题目：多线程之间按顺序调用，实现A->B->C三个线程启动，要求
 *  AA打印5次，BB打印10次，CC打印15次
 *  接着
 *  AA打印5次，BB打印10次，CC打印15次
 *  ......
 *  3轮
 *
 */
class SharedResource {
    private int number = 1;  // A:1 B:2 C:3
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            while (number != 1){
                c1.await();
            }
            for(int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            while (number != 2){
                c2.await();
            }
            for(int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            while (number != 3){
                c3.await();
            }
            for(int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        new Thread(() -> {
            for(int i = 0; i < 3; i++) {
                sharedResource.print5();
            }
        }, "A").start();

        new Thread(() -> {
            for(int i = 0; i < 3; i++) {
                sharedResource.print10();
            }
        }, "B").start();

        new Thread(() -> {
            for(int i = 0; i < 3; i++) {
                sharedResource.print15();
            }
        }, "C").start();
    }
}
