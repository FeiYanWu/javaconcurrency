package com.wyf.concurrency.chapter26;

import java.util.Random;


public class ConsumerThread extends Thread {

    private final MessageQueue queue;

    private final static Random RANDOM = new Random(System.currentTimeMillis());

    public ConsumerThread(MessageQueue queue,int seq) {
        super("Consumer"+seq);
        this.queue = queue;
    }
    @Override
    public void run() {
        while (true){
            try {
                Message message = queue.take();
                System.out.println(Thread.currentThread().getName()+" take message "+ message.getData());
                Thread.sleep(RANDOM.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }
        
    }
}
