package com.wyf.concurrency.chapter12;

import sun.reflect.generics.tree.Tree;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ThreadGroupApi {
    public static void main(String[] args) {
        ThreadGroup tg1 = new ThreadGroup("TG1");
        
        Thread t1 = new Thread(tg1,"t1"){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(10_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        tg1.setDaemon(true);
        t1.start();

        ThreadGroup tg2 = new ThreadGroup(tg1,"TG2");

        Thread t2 = new Thread(tg1,"t2"){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        t2.start();
        System.out.println(tg1.activeCount());
        System.out.println(tg1.activeGroupCount());
        
        t2.checkAccess();
        
        //tg1.destroy();
        Thread[] ts1 = new Thread[tg1.activeCount()];
        tg1.enumerate(ts1);
        Arrays.asList(ts1).forEach(System.out::println);
        System.out.println("=============================");
        tg1.enumerate(ts1,false);
        Arrays.asList(ts1).forEach(System.out::println);
        System.out.println("=============================");
        ts1 = new Thread[10];
        //Thread.currentThread().getThreadGroup().enumerate(ts1,true);
        Thread.currentThread().getThreadGroup().enumerate(ts1,false);
        Arrays.asList(ts1).forEach(System.out::println);
        
        
        //tg1.interrupt();
    }
}
