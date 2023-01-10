package com.biran.study.effectivejava.enumtest;
public class OperationTest {

    public static void main(String[] args) {
        double x = 12d;
        double y = 3d;
        for(Operation op : Operation.values()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }

        /*for (Operation op : Operation.values()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, Operation
                    .fromString(op.toString()).get().apply(x, y));
        }*/

        System.out.println(Operation.valueOf("PLUS").apply(12d, 3d));
        //System.out.println(Operation.valueOf("/").apply(12d, 3d));
        System.out.println(Operation.fromString("/").get().apply(12d, 3d));
    }
}
