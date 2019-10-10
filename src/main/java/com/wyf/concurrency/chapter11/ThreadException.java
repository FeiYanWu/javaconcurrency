package com.wyf.concurrency.chapter11;

public class ThreadException {
    private static final int A = 10;
    private static final int B = 0;
    public static void main(String[] args) {
        
        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    
                    int result = A/B;
                    System.out.println(result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
        };
        
        t.setUncaughtExceptionHandler((thread,e)->{
            System.out.println(thread);
            System.out.println(e);
        });
        t.start();
    }
}
