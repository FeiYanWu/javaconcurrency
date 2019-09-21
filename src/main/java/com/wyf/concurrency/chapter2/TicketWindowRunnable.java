package com.wyf.concurrency.chapter2;

/**************************************
 * @Author : WYF
 * @Date   : 2019/9/19 22:30
 * @Version 1.0
 *************************************/
public class TicketWindowRunnable implements Runnable {
    private String name;
    public TicketWindowRunnable(String name){
        this.name = name;
    }
    private static final int  SIZE = 50;
    private static int INDEX =1;

    @Override
    public void run(){
        while(INDEX<=SIZE){
            System.out.println(name+"当前的号码是"+(INDEX++));
        }
    }
}
