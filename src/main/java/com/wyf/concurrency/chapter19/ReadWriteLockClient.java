package com.wyf.concurrency.chapter19;
//read-write 设计模式
//read/write 设计模式
public class ReadWriteLockClient {

    public static void main(String[] args) {
        SharedData data = new SharedData(10);
        
        new ReadWorker(data).start();
        new ReadWorker(data).start();
        new ReadWorker(data).start();
        new ReadWorker(data).start();
        new ReadWorker(data).start();
        
        new WriteWorker(data,"dsewrvcdsvdsf").start();
        new WriteWorker(data,"DSEWRVCDSVDSF").start();
        
    }
}
