package com.wyf.concurrency.chapter10;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class LockTest {
    public static void main(String[] args) {
        final BooleanLock lock = new BooleanLock();
        Stream.of("T1","T2","T3","T4").forEach(name->{
            
            new Thread(()->{
                try {
                    //lock.lock();
                    
                    lock.lock(10l);
                   
                    Optional.of(Thread.currentThread().getName()+" have the lock monitor").ifPresent(System.out::println);
                    work();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Lock.TimeoutException e) {
                    Optional.of(Thread.currentThread().getName()+" time out").ifPresent(System.out::println);              
                }finally {
                    lock.unlock();
                }
            },name).start();
        });
        
        //问题
//        try {
//            Thread.sleep(10);
//            lock.unlock();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
    
    private static void work() throws InterruptedException{
        Optional.of(Thread.currentThread().getName()+" is  working").ifPresent(System.out::println);
        Thread.sleep(10_000);
    }
}
