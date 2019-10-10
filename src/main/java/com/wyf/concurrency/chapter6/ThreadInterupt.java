package com.wyf.concurrency.chapter6;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

import java.time.chrono.IsoEra;

public class ThreadInterupt {
    private static final Object MONITOR = new Object();
    public static void main(String[] args){
       /* Thread t = new Thread(){
            @Override
            public void run() {
                while(true){
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        System.out.println("收到中断信号");
//                    }
                    synchronized (MONITOR){

                        try {
                            MONITOR.wait(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            System.out.println(isInterrupted());
                        }
                    }
                    
                }
            }
        };
        Thread.sleep(100);
        System.out.println(t.isInterrupted());
        t.interrupt();
        System.out.println(t.isInterrupted());*/
        /*Thread t = new Thread(()->{
            while (true){
                synchronized (MONITOR){

                    try {
                        MONITOR.wait(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println(isInterrupted());
                    }
                }
            }
        });*/

        
        Thread main = Thread.currentThread();
        Thread t = new Thread(){

            @Override
            public void run() {
                while(true){
                    
                }
            }
        };
        t.start();
        
        Thread t2 = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //join的main线程，所以不会异常
                //t.interrupt();
                main.interrupt();
                System.out.println("interrupt");
            }
        };
        t2.start();


        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //-----------------------------
        
    }
}
