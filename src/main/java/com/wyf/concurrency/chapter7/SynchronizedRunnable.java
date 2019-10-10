package com.wyf.concurrency.chapter7;

import javax.swing.plaf.synth.SynthButtonUI;

public class SynchronizedRunnable implements Runnable {
    private static final int SIZE = 500;
    private static int INDEX = 1;

    private final static Object MONITOR = new Object();

    //锁是this
//    @Override
//    public synchronized void run(){
//        while(true){
//           
//            if(INDEX>SIZE)
//                break;
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread()+"当前的号码是"+(INDEX++));
//        }
//    }

    @Override
    public void run() {
        while (true) {
            if (ticket()) {
                break;
            }
        }
    }
    //相当于private boolean ticket(){
    //  synchronized (this){}
    //}
    private synchronized boolean ticket(){
        //getField
        if(INDEX>SIZE)
            return true;
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //getField
        //index = index + 1
        //put field index
        System.out.println(Thread.currentThread()+"当前的号码是"+(INDEX++));
        return false;
    }
}
