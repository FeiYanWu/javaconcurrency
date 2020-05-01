package com.wyf.concurrency.chapter20;

import java.util.stream.Stream;

public class ImmutablePerformance {
    public static void main(String[] args) throws InterruptedException {
        long startTimestamp = System.currentTimeMillis();
        //sync  8191
        //Immutable  7788
        SyncObj syncObj = new SyncObj();
        
        syncObj.setName("Alex");
        //多线程
        //Immutable  14968
        //sync：     17868
         Thread t1 = new Thread(){
                @Override
                public void run() {
                    for(long l=0;l<1000000;l++){
                        System.out.println(Thread.currentThread().getName()+" "+syncObj.toString());
                    }            
                 }
            };
         t1.start();
                 
        Thread t2 = new Thread(){
            @Override
            public void run() {
                for(long l=0;l<1000000;l++){
                    System.out.println(Thread.currentThread().getName()+" "+syncObj.toString());
                }
            }
        };
        t2.start();
        t1.join();
        t2.join();
        long endTimestamp = System.currentTimeMillis();
        System.out.println("Elapsed time "+(endTimestamp-startTimestamp));
    }


}

final class ImmutableObj{
    private final String name;

    ImmutableObj(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ImmutableObj{" +
                "name='" + name + '\'' +
                '}';
    }
}
class SyncObj{
    private String name;

    public synchronized void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "SyncObj{" +
                "name='" + name + '\'' +
                '}';
    }
    
}