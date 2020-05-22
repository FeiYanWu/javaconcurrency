package com.wyf.interviewA1B2C3;

import java.util.concurrent.CountDownLatch;

public class Test {
    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();
        final Object o = new Object();

        new Thread(()->{
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o){
                for (char c:aI){
                    System.out.print(c);
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        },"t1").start();

        new Thread(()->{
            synchronized (o){
                latch.countDown();
                for (char c:aC){
                    System.out.print(c);

                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        },"t2").start();
    }
}
