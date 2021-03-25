package jvm.ClassInit;

public class SuperClass {
    static{
        System.out.println("父类初始化");
    }
    public static int value = 123;
}
