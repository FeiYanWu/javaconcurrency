package com.wyf.concurrency.chapter7;

import com.wyf.concurrency.chapter6.ThreadService;

/**************************************
 * @Author : WYF
 * @Date   : 2019/9/19 22:30
 * @Version 1.0
 *************************************/
public class TicketWindowRunnable implements Runnable {
    private static final int  SIZE = 500;
    private static int INDEX =1;

    private final Object MONITOR = new Object();
    @Override
    public void run(){
        while(true){
            synchronized (MONITOR){
                if(INDEX>SIZE)
                    break;
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread()+"当前的号码是"+(INDEX++));
            }
        }
    }
}
