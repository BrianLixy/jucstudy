package com.biran.study.effectivejava.enumtest;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Operation {
    PLUS("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    };
    private final String symbol;
    Operation(String symbol) {this.symbol= symbol; }
    @Override
    public String toString() {
        return symbol;
    }
    public abstract double apply(double x, double y);

    /*
     *  初始化时，存储操作符与枚举常量的对应关系，用来实现 fromString 方法
     *  这样我们就可以通过 操作符来获取到对应的枚举常量，有点像valueOf方法，
     *  只不过它是通过枚举常量的名字name来获取常量的。这种通用的方法还可以
     *  应用到其他枚举类中
     */
    private static final Map<String, Operation> stringToEnum =
            Stream.of(values()).collect(Collectors.toMap(Object :: toString , e -> e));


    // Returns Operation for string, if any
    // 传入的字符串并不代表一项有效的操作，并强制客户端面对这种可能性（详见第55 条） 。
    public static Optional<Operation> fromString (String symbol) {
        System.out.println(stringToEnum);
        return Optional.ofNullable (stringToEnum.get (symbol));
    }

    // Switch on an enum to simulate a missing method
    // 如果一个方法不属于枚举类型，也应该在你所能控制的枚举类型上使用这种方法。这
    // 种方法有点用处，但是通常还不值得将它包含到枚举类型中去。
    public static Operation inverse(Operation op) {
        switch (op) {
            case PLUS:
                return Operation.MINUS;
            case MINUS:
                return Operation.PLUS;
            case TIMES:
                return Operation.DIVIDE;
            case DIVIDE:
                return Operation.TIMES;
            default:
                throw new AssertionError("Unknown op: " + op);
        }
    }
}
