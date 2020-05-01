package com.wyf.jcu.atomic;

import com.sun.corba.se.impl.oa.poa.AOMEntry;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AIFUTest {
    private volatile int i;
    private AtomicIntegerFieldUpdater<AIFUTest> updater = AtomicIntegerFieldUpdater.newUpdater(AIFUTest.class,"i");

    public void update(int newValue){
        updater.compareAndSet(this,i,newValue);
    }

    public int get(){
        return i;
    }
    //2.非private，protected（如果是当前类，也可以是private protected）
    //不想使用锁（显示锁，synchronized ）
    public static void main(String[] args) {
        AIFUTest test = new AIFUTest();
        test.update(10);
        System.out.println(test.get());
    }
}
