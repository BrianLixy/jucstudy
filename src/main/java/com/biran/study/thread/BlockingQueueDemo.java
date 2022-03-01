package com.biran.study.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue =  new ArrayBlockingQueue<>(3);

        /**------------------------第一组---------------------------*/
        /*blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");
        blockingQueue.add("d");

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());*/

        /**-------------------------第二组--------------------------*/

        /*System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());*/

        /**--------------------------第三组-------------------------*/
/*        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        //blockingQueue.put("d");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());*/

        /**--------------------------第四组-------------------------*/
        blockingQueue.offer("a", 5, TimeUnit.SECONDS);
        blockingQueue.offer("b", 5, TimeUnit.SECONDS);
        blockingQueue.offer("c", 5, TimeUnit.SECONDS);
        blockingQueue.offer("d", 5, TimeUnit.SECONDS);
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll(5, TimeUnit.SECONDS));

        blockingQueue.stream().forEach(System.out::println);
    }
}
