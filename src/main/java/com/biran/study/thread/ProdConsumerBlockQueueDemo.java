package com.biran.study.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource {
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void myProd() throws Exception{
        String data = null;
        boolean retValue;
        while(flag) {
            data = atomicInteger.getAndIncrement() + "";
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if(retValue) {
                System.out.println(Thread.currentThread().getName() + "插入队列成功" + data);
            } else {
                System.out.println(Thread.currentThread().getName() + "插入队列失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "生产停止");
    }

    public void myConsumer() throws Exception{
        String result = null;
        while(flag) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if(result == null || "".equalsIgnoreCase(result)) {
                flag = false;
                System.out.println(Thread.currentThread().getName() + "停止生产，退出" + result);
                return;
            }
            System.out.println(Thread.currentThread().getName() + "消费队列成功" + result);
        }
    }

    public void stop() {
        flag = false;
    }
}
public class ProdConsumerBlockQueueDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(3));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " prod start");
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Prod").start();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " consume start");
                myResource.myConsumer();
                System.out.println();
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Consume").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myResource.stop();
    }
}
