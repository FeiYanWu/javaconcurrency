package com.wyf.concurrency.chapter8;

public class DeadLock {
    private OtherService otherService ;

    public DeadLock(OtherService otherService) {
        this.otherService = otherService;
    }

    private final Object lock = new Object();
    public void m1(){
        synchronized (lock){
            otherService.s1();
            System.out.println("m1-----");
        }
        
    }
    public void m2(){
        synchronized (lock){
            System.out.println("m2-----");
        }

    }
}
