package com.wyf.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T07_SingleThreadPool {
    public static void main(String[] args) {
        //executors 线程池的工厂
        //  SingleThreadExecutor可以保证任务的顺序执行
        //为什么要单线程的线程池
        // 线程池任务队里
        // 生命周期管理
        ExecutorService service = Executors.newSingleThreadExecutor();
        for(int i=0; i<5; i++) {
            final int j = i;
            service.execute(()->{
                System.out.println(j + " " + Thread.currentThread().getName());
            });
        }

    }
}