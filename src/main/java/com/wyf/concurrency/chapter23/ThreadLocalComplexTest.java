package com.wyf.concurrency.chapter23;

import java.util.Random;

public class ThreadLocalComplexTest {
    //private final static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private final static ThreadLocalSimulator<String> threadLocal = new ThreadLocalSimulator<String>(){
        @Override
        public String initialValue() {
            return "No value";
        }
    };
    private final static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        
        Thread t1 = new Thread(){
            @Override
            public void run() {
                threadLocal.set("T1");
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" "+threadLocal.get());
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                threadLocal.set("T2");
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" "+threadLocal.get());
            }
        };
        
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();

        System.out.println("========================");

        System.out.println(Thread.currentThread().getName()+" "+threadLocal.get());
    }
}
