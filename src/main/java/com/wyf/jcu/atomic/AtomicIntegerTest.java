package com.wyf.jcu.atomic;

import java.time.temporal.ValueRange;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    
    /*
        1.内存可见
        2.sequence
        3.不保证原子性
     */
    private static Set<Integer> set = Collections.synchronizedSet(new HashSet<Integer>());
   /* 
    
   private static volatile int value;
    
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                int x = 0;
                while (x<500){
                    int temp = value;
                    System.out.println(Thread.currentThread().getName()+":"+temp);
                    value += 1;
                    *//**
                     * value = value+1；
                     * 1.get value from main memory to local memeory
                     * 2.add 1 => x
                     * 3.assign the value to x
                     * 4.flush to main memory
                     *//*
                    x++;
                }
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run() {
                int x = 0;
                while (x<500){
                    int temp = value;
                    System.out.println(Thread.currentThread().getName()+":"+temp);
                    value += 1;
                    x++;
                }
            }
        };
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }*/
    public static void main(String[] args) throws InterruptedException {
        final AtomicInteger value = new AtomicInteger();
        Thread t1 = new Thread(){
            @Override
            public void run() {
                int x = 0;
                while (x<500){
                    int v = value.getAndIncrement();
                    set.add(v);
                    System.out.println(Thread.currentThread().getName()+":"+v);
                    x++;
                }
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                int x = 0;
                while (x<500){
                    int v = value.getAndIncrement();
                    set.add(v);
                    System.out.println(Thread.currentThread().getName()+":"+v);
                    x++;
                }
            }
        };

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(set.size());
    }
}
