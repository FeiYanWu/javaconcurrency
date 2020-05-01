package com.wyf.jcu.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDetailsTest {

    public static void main(String[] args) {
       /* AtomicInteger i = new AtomicInteger();
        System.out.println(i.get());
        i = new AtomicInteger(10);
        System.out.println(i.get());
        
        i.set(13);
        System.out.println(i.get());
        
        i.lazySet(12);
        System.out.println(i.get());

        //getandadd
        
        AtomicInteger getAndAdd = new AtomicInteger(10);
        int result = getAndAdd.getAndAdd(12);
        System.out.println(result);
        System.out.println(getAndAdd.get());

        AtomicInteger addAndGet = new AtomicInteger();
        new Thread(){
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    int v = addAndGet.addAndGet(1);
                    System.out.println(v);
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    int v = addAndGet.addAndGet(1);
                    System.out.println(v);
                }
            }
        }.start();*/
       
        AtomicInteger i = new AtomicInteger(10);
        boolean flag = i.compareAndSet(12,20);
        System.out.println(i.get());
        System.out.println(flag);
    }
   
}
