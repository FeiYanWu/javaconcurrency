package com.wyf.concurrency.chapter16;

public class VolatileTest2 {
    
    //Java内存模型以及CPU缓存不一致问题的引入
    private volatile static int INIT_VALUE = 0;
    private final static int MAX_VALUE = 500;

    public static void main(String[] args) {

        new Thread(() -> {
            while (INIT_VALUE < MAX_VALUE) {
                System.out.println("t1->"+(++INIT_VALUE));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Adder-1").start();

        new Thread(() -> {
            while (INIT_VALUE < MAX_VALUE) {
                System.out.println("t2->"+(++INIT_VALUE));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Adder-2").start();
    }
    
    
}