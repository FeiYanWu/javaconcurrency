package com.wyf.concurrency.chapter17;

public class OctalObserver extends Observer {


    public OctalObserver(Subject subject){
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Binary String:"+Integer.toOctalString(subject.getState()));
    }
}
