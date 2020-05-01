package com.wyf.concurrency.chapter19;

import java.util.function.BiFunction;

public class SharedData {
    private final char[] buffer;
    private final ReadWriteLock LOCK = new ReadWriteLock();
    
    
    public SharedData(int size){
        this.buffer = new char[size];
        for(int i = 0;i<size;i++){
            this.buffer[i] ='*'; 
        }
    }
    
    public char[] read() throws InterruptedException {
        try {
            LOCK.readLock();
            return this.doRead();
        } finally {
            LOCK.readUnlock();
        }
    }

    private char[] doRead() {
        char[] readBuf = new char[buffer.length];
        for(int i = 0;i< buffer.length;i++){
            readBuf[i] = buffer[i];
        }
        
        slowly(50);
        return readBuf;
    }
    
    public void write(char c) throws InterruptedException {
        try {
            LOCK.writeLock();
            doWrite(c);
            
        } finally {
            LOCK.writeUnLock();
        }
    }

    private void doWrite(char c) {
        for(int i=0;i<buffer.length;i++){
            buffer[i] = c;
            
        }
        slowly(10);
    }

    private void slowly(int mills){
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
