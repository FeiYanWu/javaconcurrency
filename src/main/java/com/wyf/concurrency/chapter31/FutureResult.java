package com.wyf.concurrency.chapter31;

public class FutureResult implements Result{
    
    private Result result;
    private boolean ready = false;
    
    public synchronized void setResult(Result result){
        this.result = result;
        this.ready = true;
        this.notifyAll();
    }
    @Override
    public synchronized Object resultValue() {
        while(!ready){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.result.resultValue();
    }
}
