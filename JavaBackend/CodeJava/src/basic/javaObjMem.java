package basic;

import org.openjdk.jol.info.ClassLayout;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/3/2
 */
public class javaObjMem {
    // 测试java对象内存布局
    public static class WBQ{
        double fool;
    }

    public static void reEntry(Object obj){
        // 再次进入锁
        synchronized (obj){
            System.out.println("Re Entry block.");
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
    }
    public static void synTest(){
        WBQ wbq = new WBQ();
        System.out.println(ClassLayout.parseInstance(wbq).toPrintable());
        // 调用hashcode方法
        System.out.println("Hash:"+Integer.toHexString(wbq.hashCode()));
        System.out.println(ClassLayout.parseInstance(wbq).toPrintable());
        // 加锁
        synchronized (wbq){
            System.out.println("In Synchronized block.");
            System.out.println(ClassLayout.parseInstance(wbq).toPrintable());
            reEntry(wbq);
            System.out.println("Return Synchronized block.");
            System.out.println(ClassLayout.parseInstance(wbq).toPrintable());
        }
        System.out.println("Out Synchronized block.");
        System.out.println(ClassLayout.parseInstance(wbq).toPrintable());

        // 数组
        WBQ[] wbqs = new WBQ[4];
        System.out.println("对象数组");
        // 对象的数组只是存储对象的引用，数组中分配的空间也只是引用的大小n*4byte
        System.out.println(ClassLayout.parseInstance(wbqs).toPrintable());
        for (int i=0; i<4; i++){
            wbqs[i] = new WBQ();
        }
        System.out.println(ClassLayout.parseInstance(wbqs).toPrintable());
        System.out.println(ClassLayout.parseInstance(wbqs[0]).toPrintable());

        // 数组
        double [] doubles = new double[4];
        System.out.println("基本类型数组");
        System.out.println(ClassLayout.parseInstance(doubles).toPrintable());

    }
    public static void main(String [] args){
        synTest();
    }


}
