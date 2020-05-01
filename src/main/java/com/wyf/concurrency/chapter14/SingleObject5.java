package com.wyf.concurrency.chapter14;

public class SingleObject5 {
    /**
     * double check
     */
    private static volatile SingleObject5 instance;
    private SingleObject5(){}

    public static SingleObject5 getInstance(){
        if(null == instance){
            synchronized (SingleObject5.class){
                if(null == instance){
                    instance = new SingleObject5();
                }
            }
        }
        return instance;
    }
}
