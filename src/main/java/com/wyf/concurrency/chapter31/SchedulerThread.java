package com.wyf.concurrency.chapter31;

public class SchedulerThread<run> extends Thread {
    private final ActivationQueue activationQueue;

    public SchedulerThread(ActivationQueue activationQueue) {
        this.activationQueue = activationQueue;
    }
    
    public void invoke(MethodRequest request){
        this.activationQueue.put(request);
    }

    @Override
    public void run() {
        while (true){
            activationQueue.take().execute();
        }
    }
}
