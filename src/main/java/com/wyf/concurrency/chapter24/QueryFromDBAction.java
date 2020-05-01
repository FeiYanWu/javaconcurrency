package com.wyf.concurrency.chapter24;

import javax.swing.*;

public class QueryFromDBAction {
    
//    public void execute(Context context){
//        try {
//            Thread.sleep(1000L);
//            String name = "Alex "+ Thread.currentThread().getName();
//            context.setName(name);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    public void execute(){
        try {
            Thread.sleep(1000L);
            String name = "Alex "+ Thread.currentThread().getName();
            ActionContext.getActionContext().getContext().setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
