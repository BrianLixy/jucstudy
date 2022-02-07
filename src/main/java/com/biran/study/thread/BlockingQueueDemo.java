package com.biran.study.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue =  new ArrayBlockingQueue<>(3);

        blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");
        blockingQueue.add("c");

        blockingQueue.stream().forEach(System.out::println);
    }
}
