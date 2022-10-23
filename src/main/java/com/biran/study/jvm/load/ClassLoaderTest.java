package com.biran.study.jvm.load;

public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader); //sun.misc.Launcher$AppClassLoader@18b4aac2

        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader); //sun.misc.Launcher$ExtClassLoader@1540e19d

        ClassLoader bootstrapClassloader = extClassLoader.getParent();
        System.out.println(bootstrapClassloader); //null

        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader); //sun.misc.Launcher$AppClassLoader@18b4aac2
    }
}
