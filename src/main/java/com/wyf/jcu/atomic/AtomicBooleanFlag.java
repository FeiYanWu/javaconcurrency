package com.wyf.jcu.atomic;

import org.omg.CORBA.FloatSeqHelper;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanFlag {
    private static AtomicBoolean flag = new AtomicBoolean(true);
    public static void main(String[] args) throws InterruptedException {
        new Thread(){
            @Override
            public void run() {
                while (flag.get()){
                    System.out.println("working");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("finish");
            }
        }.start();
        
        Thread.sleep(5000);

        flag.set(false);
    }
}
