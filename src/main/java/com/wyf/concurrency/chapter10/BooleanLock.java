package com.wyf.concurrency.chapter10;

import java.text.CollationKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class BooleanLock implements Lock {
    
    //False :  lock free
    //True ：  have been get
    private boolean initValue;
    private Collection<Thread> blockedThreadCollection = new ArrayList<>();
    
    
    private Thread currentThread;
    
    public BooleanLock(){
        this.initValue = false;
    }
    @Override
    public synchronized void lock() throws InterruptedException {
        while(initValue){
            blockedThreadCollection.add(Thread.currentThread());
            this.wait();
        }
        this.initValue = true;
        blockedThreadCollection.remove(Thread.currentThread());
        
        currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void unlock() {
        
        if(Thread.currentThread() == currentThread){
            this.initValue = false;
            Optional.of(Thread.currentThread().getName()+" released the lock").ifPresent(System.out::println);
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlockedSize() {
        return blockedThreadCollection.size();
    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException,TimeoutException {
        if(mills<=0){
            lock();
        }    
        long hasRemaining = mills;
        long endTime = System.currentTimeMillis()+mills;
        
        while (initValue){
            if(hasRemaining<=0){
                throw new TimeoutException("Time out"); 
            }
            blockedThreadCollection.add(Thread.currentThread());
            this.wait(mills);
            hasRemaining =endTime - System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName()+" "+hasRemaining);
        }
        
        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }
}
