package com.wyf.concurrency.chapter4;

public class DaemonThread2 {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            Thread innerThread = new Thread(()->{
                try {
                    while(true){
                        //可以做心跳守护
                        System.out.println("Do something for health check");
                        Thread.sleep(1_000);
                    }
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            innerThread.setDaemon(true);
            innerThread.start();

            try {
                Thread.sleep(10_000);
                System.out.println("t is done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
    }
}