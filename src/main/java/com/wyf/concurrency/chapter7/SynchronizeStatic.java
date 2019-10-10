package com.wyf.concurrency.chapter7;

public class SynchronizeStatic {
    
    
    static {
        synchronized (SynchronizeStatic.class){
            try {
                System.out.println("static:"+Thread.currentThread().getName());
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static synchronized  void m1(){
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }        
        
    }

    public static synchronized  void m2(){
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void m3(){
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
