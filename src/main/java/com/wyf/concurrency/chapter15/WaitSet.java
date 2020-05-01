package com.wyf.concurrency.chapter15;

import com.wyf.concurrency.chapter10.Lock;
import org.omg.PortableServer.THREAD_POLICY_ID;

import javax.swing.plaf.synth.SynthRadioButtonMenuItemUI;
import java.awt.*;
import java.util.stream.IntStream;

public class WaitSet {
    private final static Object LOCK  = new Object();

    private static void work(){
        synchronized (LOCK){
            System.out.println("Begin ---");
            try {
                System.out.println("Thread will coming");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread will out");
        }
    }
    
    
    /**
     * 1所有的对象都有wait set,用来存放调用了该对象wait方法之后进入block状态
     * 2线程被notify之后不会立即得到运行
     * 3线程从wait set中被唤醒顺序不一定是FIFO
     * 4线程被唤醒后必须重新获取锁
     * @param args
     */
    public static void main(String[] args) {
        
        new Thread(){
            @Override
            public void run() {
                //此线程进入lock中的waitset中
                work();
            }
        }.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (LOCK){
            LOCK.notify();
        }
//        IntStream.rangeClosed(1,10).forEach(i->new Thread(String.valueOf(i)){
//            @Override
//            public void run() {
//                synchronized (LOCK){
//                    System.out.println(Thread.currentThread().getName()+" will come to wait set");
//                    try {
//                        LOCK.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(Thread.currentThread().getName()+" will leave from wait set");
//                }
//            }
//        }.start());
//
//        try {
//            Thread.sleep(3_000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        IntStream.rangeClosed(1,10).forEach(i->{
//            synchronized (LOCK){
//                LOCK.notify();
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }
}
