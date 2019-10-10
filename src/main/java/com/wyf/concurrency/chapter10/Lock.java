package com.wyf.concurrency.chapter10;

import java.util.Collection;
import java.util.concurrent.Executors;

public interface Lock {
    
    class TimeoutException extends Exception{
        public TimeoutException(String message){
            super(message);
        }
    }
    void lock() throws InterruptedException;
    
    void unlock();
    
    Collection<Thread> getBlockedThread();
    
    int getBlockedSize();

    void lock(long mills) throws InterruptedException, TimeoutException;
}
