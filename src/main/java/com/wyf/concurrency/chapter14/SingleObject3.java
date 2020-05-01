package com.wyf.concurrency.chapter14;

public class SingleObject3 {

    /**
     * 性能不怎么样
     */
    private static SingleObject3 instance;
    private SingleObject3(){}

    
    public synchronized static SingleObject3 getInstance(){
        if(null == instance){
            instance = new SingleObject3();
        }
        return instance;
    }
}
