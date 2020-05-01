package com.wyf.concurrency.chapter16;

public class VolatileTest {

    private volatile static int INIT_VALUE = 0;
    private final static int MAX_VALUE = 500;
    
    public static void main(String[] args) {

        new Thread(()->{
            int localValue = INIT_VALUE;
            while(localValue < MAX_VALUE){
                if(localValue != INIT_VALUE){
                    System.out.printf("The value updated to %d\n",INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        },"Reader").start();

        new Thread(()->{
            int localValue = INIT_VALUE;
            while(INIT_VALUE < MAX_VALUE){
                System.out.printf("update value to %d\n",++localValue);
                INIT_VALUE = localValue;

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Update").start();
    }
}
