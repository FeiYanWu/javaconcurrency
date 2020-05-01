package com.wyf.concurrency.chapter14;

public class SingleObject6 {
    /**
     * 第一次加载Singleton类时不会去初始化mSington，只有第一次调用getInstance，虚拟机加载SingletonHolder
     *
     * 并初始化Sington，这样不仅能确保线程安全，也能保证 Singleton 类的唯一性
     */
    private SingleObject6(){
        
    }
    private static class InstanceHolder{
        private final static SingleObject6 instance = new SingleObject6();
    }
    public static SingleObject6 getInstance(){
        return InstanceHolder.instance;
    }
}   
