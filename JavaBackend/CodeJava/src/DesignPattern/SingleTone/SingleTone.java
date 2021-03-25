package DesignPattern.SingleTone;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/2/16
 */
public class SingleTone {
    // 饿汉模式-线程安全
    private static SingleTone instance = new SingleTone();
    private SingleTone(){}
    public static SingleTone getInstance(){
        return instance;
    }
}
