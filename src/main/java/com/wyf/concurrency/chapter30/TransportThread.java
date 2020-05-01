package com.wyf.concurrency.chapter30;

import java.util.Random;

/**
 * 向传送带扔东西
 */
public class TransportThread extends Thread {
    
    private final Channel channel;
    
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public TransportThread(String name,Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        try {
            for(int i=0;true;i++){
                Request request = new Request(getName(),i);
                this.channel.put(request);
                Thread.sleep(RANDOM.nextInt(1000));
            }
        }catch (Exception e){
            
        }
    }
}
