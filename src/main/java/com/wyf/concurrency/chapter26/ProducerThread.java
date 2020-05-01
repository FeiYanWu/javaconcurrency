package com.wyf.concurrency.chapter26;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerThread extends Thread {

    private final MessageQueue queue;

    private final static Random RANDOM = new Random(System.currentTimeMillis());
    
    private final static AtomicInteger counter = new AtomicInteger(0);
    public ProducerThread(MessageQueue queue,int seq) {
        super("Producer"+seq);
        this.queue  = queue;
    }

    @Override
    public void run() {
        
        while(true){
            try {
                Message message = new Message("Message-"+counter.getAndIncrement());
                queue.put(message);
                System.out.println(Thread.currentThread().getName()+" put message "+message.getData());
                Thread.sleep(RANDOM.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
