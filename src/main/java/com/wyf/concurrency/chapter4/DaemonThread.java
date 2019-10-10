package com.wyf.concurrency.chapter4;

public class DaemonThread {
    public static void main(String[] args) throws Exception{
        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+" Running");
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName()+" done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };  //new 
        t.start();  //runnable ->running   ->dead  ->blocked  
        System.out.println(Thread.currentThread().getName());
    }
}
