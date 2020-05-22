package com.wyf.interviewA1B2C3;

public class T03_00_sync_wait_notify {

    private static volatile boolean t2started = false;

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();
        final Object o = new Object();

        new Thread(()->{
            synchronized (o){
                while (!t2started){
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
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
                t2started = true;
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
