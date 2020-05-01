package com.wyf.concurrency.chapter14;

public class SingleObject2 {
    private static SingleObject2 instance;
    private SingleObject2(){}
    
    //线程不安全
    public static SingleObject2 getInstance(){
        if(null == instance){
            instance = new SingleObject2();
        }
        return instance;
    }
}
