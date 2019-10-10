package com.wyf.concurrency.chapter8;

public class OtherService {
    
    private DeadLock deadLock;

    public void setDeadLock(DeadLock deadLock) {
        this.deadLock = deadLock;
    }

    private final Object lock =new Object();
    
    public void s1(){
        synchronized (lock){
            System.out.println("s1------------");
        }
    }
    public void s2(){
        synchronized (lock){
            System.out.println("s1------------");
            deadLock.m2();
        }
    }
    
}
