package com.biran.study.jvm.load;

/**
 * 1 验证 verify
 * 2 准备 prepare
 *  为类变量分配内存并且设置该类变量的默认初始值
 * 3 解析 resolve
 *  将常量池内的符号引用转换为直接引用的过程
 */
public class ClassLinkingTest {
    // prepare : 类变量 a = 0, ---> initial : a = 1;
    // 不包含final修饰的static， final在编译的时候就会分配了
    private static int a = 1;

    public static void main(String[] args) {
        System.out.println(a);
    }
}
