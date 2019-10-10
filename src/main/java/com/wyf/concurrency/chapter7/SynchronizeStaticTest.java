package com.wyf.concurrency.chapter7;

public class SynchronizeStaticTest {
    public static void main(String[] args) {

        new Thread(()->{
            SynchronizeStatic.m1();
        },"t1").start();
        new Thread(()->{
            SynchronizeStatic.m2();
        },"t2").start();

        new Thread(()->{
            SynchronizeStatic.m3();
        },"t3").start();
    }
}
