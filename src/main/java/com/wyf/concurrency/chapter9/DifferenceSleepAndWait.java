package com.wyf.concurrency.chapter9;

import java.util.stream.Stream;

public class DifferenceSleepAndWait {
   
    private final static Object LOCK = new Object();
    public static void main(String[] args) {
        Stream.of("T1","T2").forEach(n->
                new Thread(n){
                    @Override
                    public void run() {
                        //m1();
                        m2();
                    }
                }.start()
        );
    }
    
    private static void m1(){
        synchronized (LOCK){
            try {
                System.out.println("The thread "+Thread.currentThread().getName()+" Enter");
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
     
    }
    
    private static void m2(){
        synchronized (LOCK){
            try {
                System.out.println("The thread "+Thread.currentThread().getName()+"Enter");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
