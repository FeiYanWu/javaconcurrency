package com.wyf.jcu.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDetailsTest2 {

    
    private final static CompareAndSetLock lock = new CompareAndSetLock();
    public static void main(String[] args) {
        for(int i =0 ;i<2;i++) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        doSomeThing2();
                    } catch (InterruptedException | GetLockException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
   
    
    public static void doSomeThing() throws InterruptedException {
        synchronized (AtomicIntegerDetailsTest2.class){
            System.out.println(Thread.currentThread().getName()+" get the lock");
            Thread.sleep(100000);
        }
    }

    public static void doSomeThing2() throws InterruptedException, GetLockException {
        try {         
            lock.tryLock();
            System.out.println(Thread.currentThread().getName()+" get the lock");
            Thread.sleep(100000);
        }finally {
            lock.unlock();
        }
    }
}
