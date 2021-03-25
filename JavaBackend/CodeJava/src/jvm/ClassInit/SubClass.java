package jvm.ClassInit;


public class SubClass extends SuperClass{
    static{
        System.out.println("子类初始化");
    }
}