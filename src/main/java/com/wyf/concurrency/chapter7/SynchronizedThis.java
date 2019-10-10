package com.wyf.concurrency.chapter7;

public class SynchronizedThis {

    public static void main(String[] args) {
        
        ThisLock thisLock = new ThisLock();
        new Thread(()->{
            thisLock.m1();
        },"t1").start();
        new Thread(()->{
            thisLock.m2();
        },"t2").start();
    }
}
class ThisLock{
    
    public synchronized void m1(){
       
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void m2(){

        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}