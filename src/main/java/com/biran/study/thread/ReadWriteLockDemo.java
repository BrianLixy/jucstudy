package com.biran.study.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        rwlock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "writing:" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "writed.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwlock.writeLock().unlock();
        }
    }

    public Object get(String key) {
        rwlock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "reading:" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object object = map.get(key);
            System.out.println(Thread.currentThread().getName() + "readed." + object);
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwlock.readLock().unlock();
        }
        return null;
    }
}
public class ReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);

        MyCache myCache = new MyCache();

        for(int i = 0; i < 5; i++) {
            String finalI = String.valueOf(i);
            new Thread(() -> {
                myCache.put(finalI, finalI);
                latch.countDown();
            }, finalI).start();
        }

        for(int i = 0; i < 5; i++) {
            String finalI = String.valueOf(i);
            new Thread(() -> {
                myCache.get(finalI);
                latch.countDown();
            }, finalI).start();
        }

        latch.await();
        System.out.println(Thread.currentThread().getName() + " finished");
    }
}
