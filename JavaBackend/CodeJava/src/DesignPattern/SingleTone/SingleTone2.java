package DesignPattern.SingleTone;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/2/16
 */
public class SingleTone2 {
    // 懒汉模式-使用时才初始化实例
    private static SingleTone2 instance;
    private SingleTone2(){}
    // 1. 单线程版本
    public SingleTone2 getInstance(){
        if (instance==null){
            instance=new SingleTone2();
        }
        return instance;
    }
    // 2. 加锁实现多线程，但效率低
    public SingleTone2 getInstance2(){
        synchronized (SingleTone2.class){
            if (instance==null){
                instance = new SingleTone2();
            }
            return instance;
        }
    }
    // 3. 双检查，在没有初始化时才加锁,
    // reorder 导致不安全，可以将instance设置为volatile
    // 可能得到不为null但是没有初始化的instance
    public SingleTone2 getInstance3(){
        if (instance == null){
            synchronized (SingleTone2.class){
                if (instance==null){
                    instance = new SingleTone2();
                }
            }
        }
        return instance;
    }
}