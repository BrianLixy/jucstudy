package com.biran.study.effectivejava.methods;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SetList {
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();
        List<Integer> list = new ArrayList<>();

        for(int i = -3; i < 3; i++) {
            set.add(i);
            list.add(i);
        }
        System.out.println(set + " " + list);
        for(int i = 0; i < 3; i++) {
            set.remove(i);
            list.remove(i);
        }
        System.out.println(set + " " + list);


/*        new Thread(System.out::println).start();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(System.out::println);
        executorService.submit(() -> System.out.println());
        executorService.submit(System.out::println);*/

        /*for(int i = 0; i < 3; i++) {
            set.remove(i);
            list.remove((Integer) i);
        }*/
    }
}
