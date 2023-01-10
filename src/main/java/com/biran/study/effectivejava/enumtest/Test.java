package com.biran.study.effectivejava.enumtest;

public class Test {
    // The int enum pattern - severely deficient!
    public static final int APPLE_FUJI = 0;
    public static final int APPLE_PIPPIN = 1;
    public static final int APPLE_GRANNY_SMITH = 2;
    public static final int ORANGE_NAVEL = 0;
    public static final int ORANGE_TEMPLE = 1;
    public static final int ORANGE_BLOOD = 2;

    public enum Apple { FUJI, PIPPIN, GRANNY, SMITH }
    public enum Orang { NAVEL, TEMPLE, BLOOD }

    public static void main(String[] args) {
        System.out.println((APPLE_FUJI - ORANGE_TEMPLE) / APPLE_PIPPIN);
        System.out.println(APPLE_FUJI == ORANGE_TEMPLE);
        System.out.println(Apple.FUJI == Apple.PIPPIN);
        //System.out.println(Apple.FUJI == Orang.TEMPLE);
        //System.out.println((Apple.FUJI - Orang.TEMPLE) / Apple.PIPPIN);
    }

}
