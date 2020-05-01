package com.wyf.concurrency.chapter10;

public class SynchronizedProblem {

    public static void main(String[] args) throws InterruptedException{
        new Thread(){
            @Override
            public void run() {
                work();
            }
        }.start();
        
        Thread.sleep(1000);
        
        //如果t2想打断t1来执行
        Thread t2 = new Thread(){
            @Override
            public void run() {
                work();
            }
        };
        
        t2.start();
        Thread.sleep(2000);
        t2.interrupt();
        System.out.println(t2.isInterrupted());
    }
    //synchronized 不可中断
    private synchronized static void work(){
        System.out.println(Thread.currentThread().getName());
        while(true){

        }
    }
}
