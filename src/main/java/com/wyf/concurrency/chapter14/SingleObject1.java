package com.wyf.concurrency.chapter14;

public class SingleObject1 {
    /**
     * 饿汉模式
     * can't lazy load
     * 
     * 不能我需要的时候去创建
     */
    private static final SingleObject1 instance = new SingleObject1();
    private SingleObject1(){}
    
    public static SingleObject1 getInstance(){
        return instance;
    }
    
}
