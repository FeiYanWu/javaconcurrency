package com.wyf.concurrency.chapter27;

public class CountDown {
    
    private int total;

    public CountDown(int total) {
        this.total = total;
    }
    
    private static int counter;
    
    public void down(){
        synchronized (this){
            counter++;
            this.notifyAll();
        }
    }
    
    public void await() throws InterruptedException {
        synchronized (this){
            while (total != counter){
                this.wait();
            }
        }
    }
}
