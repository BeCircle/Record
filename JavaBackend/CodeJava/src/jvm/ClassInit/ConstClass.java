package jvm.ClassInit;

public class ConstClass {
    static {
        System.out.println("常量类初始化");
    }
    public static final String HELLOWORLD = "Hello World";
}
