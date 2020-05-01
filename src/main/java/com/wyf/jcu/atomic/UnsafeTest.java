package com.wyf.jcu.atomic;


import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnsafeTest {
    public static void main(String[] args) throws Exception {
//        Unsafe unsafe = Unsafe.getUnsafe();
//        System.out.println(unsafe);
//        Unsafe unsafe = getUnsafe();
//        System.out.println(unsafe);

        ExecutorService service = Executors.newFixedThreadPool(1000);

        /**
         * StupiedCounter
         *          Counter result:9769350
         *          Time passed in ms:219
         * SyncCounter
         *          Counter result:10000000
         *          Time passed in ms:772
         *
         * LockCounter
         *          Counter result:10000000
         *          Time passed in ms:702
         *
         * AtomicCounter
         *          Counter result:10000000
         *          Time passed in ms:520
         *
         * CasCounter
         *          Counter result:10000000
         *          Time passed in ms:997
         */

        Counter counter = new CasCounter();

        long start = System.currentTimeMillis();
        for(int i=0;i<1000;i++){
            service.submit(new CounterRunnable(counter,10000));
        }
        service.shutdown();

        service.awaitTermination(1, TimeUnit.HOURS);
        long end = System.currentTimeMillis();
        System.out.println("Counter result:"+counter.getCounter());
        System.out.println("Time passed in ms:"+(end-start));
    }

    static class StupiedCounter implements Counter{
        private long counter = 0;
        @Override
        public void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class SyncCounter implements Counter{
        private long counter = 0;
        @Override
        public synchronized void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    //显示锁
    static class LockCounter implements Counter{
        private long counter = 0;
        //默认不公平
        private final Lock lock = new ReentrantLock();
        @Override
        public void increment() {
            try {
                lock.lock();
                counter++;
            } finally {
                lock.unlock();
            }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class CasCounter implements Counter{
        private volatile long counter = 0;

        private Unsafe unsafe;

        private long offset;

        CasCounter() throws Exception{
            unsafe = getUnsafe();
            offset = unsafe.objectFieldOffset(CasCounter.class.getDeclaredField("counter"));
        }

        @Override
        public void increment() {
            long current = counter;
            while (!unsafe.compareAndSwapLong(this,offset,current,current+1)){
                current = counter;
            }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class AtomicCounter implements Counter{
        private AtomicLong counter = new AtomicLong();
        @Override
        public void increment() {
            counter.incrementAndGet();
        }

        @Override
        public long getCounter() {
            return counter.get();
        }
    }
    public static Unsafe getUnsafe(){
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    interface Counter{
        void increment();
        long getCounter();
    }


    static class CounterRunnable implements Runnable{
        private final Counter counter;
        private final int num;

        public CounterRunnable(Counter counter, int num) {
            this.counter = counter;
            this.num = num;
        }

        @Override
        public void run() {
            for (int i=0;i<num;i++){
                counter.increment();
            }
        }
    }
}