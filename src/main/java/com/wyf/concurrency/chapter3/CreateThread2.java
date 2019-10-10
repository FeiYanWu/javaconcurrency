package com.wyf.concurrency.chapter3;


import java.util.Arrays;
import java.util.Calendar;

public class CreateThread2 {
    public static void main(String[] args) {

        Thread t = new Thread();
        t.start();
        System.out.println(t.getThreadGroup());
        System.out.println(Thread.currentThread().getThreadGroup());
        
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println(threadGroup.activeCount());

        
        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        Arrays.asList(threads).forEach(System.out::println);

        System.out.println(getCurDay());
    }

    public static int getCurDay() {
        final Calendar cal = Calendar.getInstance();
        return cal.get(5);
    }
}
