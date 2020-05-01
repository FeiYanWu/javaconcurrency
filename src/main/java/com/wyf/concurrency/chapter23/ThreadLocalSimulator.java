package com.wyf.concurrency.chapter23;


import java.util.HashMap;
import java.util.Map;
/*
始终当前线程作为key
 */
public class ThreadLocalSimulator<T> {
    
    private final Map<Thread,T> storage = new HashMap<Thread,T>();
    
    
    public void set(T t){
        synchronized (this){
            Thread key = Thread.currentThread();
            storage.put(key,t);
        }
    }
    public T get(){
        synchronized (this){
            Thread key = Thread.currentThread();
            T value = storage.get(key);
            if(value == null){
                return initialValue();
            }
            return value;
        }
    }
    public T initialValue(){
        return null;
    }
}
