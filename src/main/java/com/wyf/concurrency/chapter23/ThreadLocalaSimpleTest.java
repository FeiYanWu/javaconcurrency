package com.wyf.concurrency.chapter23;

import sun.java2d.pipe.SpanIterator;

public class ThreadLocalaSimpleTest {
    private static ThreadLocal<String> threadLocal = new ThreadLocal(){
        @Override
        protected String initialValue() {
            return "Alex";
        }
    };

    public static void main(String[] args) throws InterruptedException {
        //threadLocal.set("Alex");
        Thread.sleep(1000);
        System.out.println(threadLocal.get());
    }
}
