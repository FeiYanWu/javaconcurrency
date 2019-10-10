package com.wyf.concurrency.chapter5;

import java.util.Optional;
import java.util.stream.IntStream;

public class ThreadJoin2 {
    public static void main(String[] args) throws InterruptedException {
        
        /*Thread t1 = new Thread(()->{
            System.out.println("Thread is running");
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 is doe");
        });
        
        t1.start();
        t1.join(100);
        Optional.of("All of task finish").ifPresent(System.out::println);
        IntStream.range(1,10000).forEach(i-> System.out.println(Thread.currentThread().getName()+"->"+i));*/
        
        //main线程等待main线程结束
        Thread.currentThread().join();
    }
}
