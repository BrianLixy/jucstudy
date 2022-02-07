package com.biran.study.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("coming callable**********");
        return 100;
    }
}
public class CallableDemo {
    public static void main(String[] args) throws Exception {
        FutureTask<Integer> futureTask = new FutureTask(new MyThread());
        new Thread(futureTask, "AA").start();
        new Thread(futureTask, "BB").start();

        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(futureTask.get());
    }
}
