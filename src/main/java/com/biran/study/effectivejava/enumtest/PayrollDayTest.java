package com.biran.study.effectivejava.enumtest;

public class PayrollDayTest {
    public static void main(String[] args) {
        System.out.println(PayrollDayNonOptimal.MONDAY.pay(9*60, 1));
        System.out.println(PayrollDayNonOptimal.SUNDAY.pay(9*60, 1));

        System.out.println(PayrollDay.MONDAY.pay(9*60, 1));
        System.out.println(PayrollDay.SUNDAY.pay(9*60, 1));
    }
}
