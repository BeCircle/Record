package DesignPattern.SingleTone;

/**
 * Author: wbq813@foxmail.com
 * Copyright: http://codeyourlife.cn
 * Platform: Win10 Jdk8
 * Date: 2020/2/16
 */
public class SingleTone3 {
    // 4. 基于内部类实现线程安全版本
    // 内部类的成员变量被访问时才会加载这个类，加载时成员变量才会被初始化
    private static class SingleToneHolder{
        static SingleTone3 instance = new SingleTone3();
    }
    private SingleTone3(){}
    public SingleTone3 getInstance(){
        return SingleToneHolder.instance;
    }
}
