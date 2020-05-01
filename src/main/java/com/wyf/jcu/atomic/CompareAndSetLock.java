package com.wyf.jcu.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class CompareAndSetLock {
    
    private final AtomicInteger value = new AtomicInteger(0);
    private Thread localThread;
    public void tryLock() throws GetLockException {
        boolean success = value.compareAndSet(0,1);
        if(!success)
            throw new GetLockException(" get the lock failed");
        else 
            localThread = Thread.currentThread();
    }
    
    public void unlock(){
        
        if(0 == value.get()){
            return;
        }
        if(localThread == Thread.currentThread())
            value.compareAndSet(1,0);
    }
}
