package com.biran.study.jvm.load;

import com.sun.org.apache.xpath.internal.objects.XNumber;

public class ClassInitTest {

    private static int number = 20;
    static {
        num = 10;
        // Illegal forward reference
        //System.out.println(num);
        number = 30;
    }
    private static int num = 5;

    static class Father {
        public static int A = 1;
        static {
            A =2;
        }
    }

    static class Son extends Father {
        public static int B = A;
    }

    public static void main(String[] args) {
        System.out.println(ClassInitTest.num);
        System.out.println(ClassInitTest.number);
        System.out.println(Son.B);
    }
}
