package com.wyf.threadpool;

import java.util.concurrent.*;

public class T06_00_Future {
    public static void main(String[] args) throws Exception{
        //FutureTask  既是future 也是runnable
        FutureTask<Integer> task = new FutureTask(()->{
            TimeUnit.MICROSECONDS.sleep(500);
            return 1;
        });
        new Thread(task).start();
        System.out.println(task.get());

        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer> f = service.submit(()->{
            TimeUnit.MICROSECONDS.sleep(500);
            return 1;
        });
        System.out.println(f.get()) ;
        System.out.println(f.isDone());
    }
}
