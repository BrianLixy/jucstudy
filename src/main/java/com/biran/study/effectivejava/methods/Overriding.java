package com.biran.study.effectivejava.methods;

import java.util.Arrays;
import java.util.List;

class Wine {
    String name() {
        return "wine";
    }
}

class SparklingWine extends Wine {
    @Override
    String name() {
        return "sparkling Wine";
    }
}

class Champagne extends SparklingWine {
    @Override
    String name() {
        return "champagne";
    }
}
public class Overriding {
    public static void main(String[] args) {
        List<Wine> wineList = Arrays.asList(new Wine(), new SparklingWine(), new Champagne());
        wineList.forEach(wine -> System.out.println(wine.name()));
    }
}
