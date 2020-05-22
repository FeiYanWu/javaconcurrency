package com.wyf.container;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
//给线程传递任务
//两个线程 一个对象把东西当面给另一个对象   ，没人接就阻塞
public class T08_SynchronusQueue { //容量为0
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.put("aaa"); //阻塞等待消费者消费
        //strs.put("bbb");
        //strs.add("aaa");
        System.out.println(strs.size());
    }
}