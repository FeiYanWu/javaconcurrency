package com.wyf.concurrency.chapter28;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageHandler {
    
    private final static Random RANDOM = new Random(System.currentTimeMillis());
    
    private final static Executor executor = Executors.newFixedThreadPool(5);

    public void request(Message message){

        executor.execute(()->{
            System.out.println(Thread.currentThread().getName()+" handle the message " +message.getValue());
            try {
                Thread.sleep(RANDOM.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        
//        new Thread(()->{
//            System.out.println(Thread.currentThread().getName()+" handle the message " +message.getValue());
//            try {
//                Thread.sleep(RANDOM.nextInt(1000));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
    }
    
    public void shutdown(){
        ((ExecutorService)executor).shutdown();
    }
}
