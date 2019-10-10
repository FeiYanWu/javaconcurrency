package com.wyf.concurrency.chapter2;

/**************************************
 * @Author : WYF
 * @Date   : 2019/9/19 22:24
 * @Version 1.0
 *************************************/
public class Bank {
    public static void main(String[] args){
//        TicketWindow tw  = new TicketWindow("一号柜台");
//        tw.start();
//        TicketWindow tw2  = new TicketWindow("二号柜台");
//        tw2.start();
        
        Thread t1 = new Thread(new TicketWindowRunnable(),"t1");
        t1.start();

        Thread t2 = new Thread(new TicketWindowRunnable(),"t2");
        t2.start();

    }
}
