package com.wyf.interviewA1B2C3;

import javax.sound.midi.Soundbank;

public class T02_00_sync_wait_notify {

    public static void main(String[] args) {
        final Object o = new Object();

        char[] aI = "1234567".toCharArray();

        char[] aC = "ABCDEFG".toCharArray();

        new Thread(()->{
            synchronized (o){
                for(char c:aI){
                    System.out.println(c);
                    try {
                        o.notify();
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
                for(char c:aC){
                    System.out.println(c);
                    try {
                        o.notify();
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
