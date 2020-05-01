package com.wyf.concurrency.chapter30;

public class WorkerClient {

    public static void main(String[] args) {
        final Channel channel = new Channel(5);
        
        channel.startWork();
        
        
        new TransportThread("Alex",channel).start();
        new TransportThread("Jack",channel).start();
        new TransportThread("John",channel).start();
    }
}
