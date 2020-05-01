package com.wyf.concurrency.chapter22;

import com.sun.org.apache.regexp.internal.RE;

import javax.sound.midi.Soundbank;
import java.util.Random;
import java.util.stream.Stream;

public class ClientThread extends Thread {

    private final RequestQueue queue;
    private final Random random;
    private final String seedValue;
    
    public ClientThread(RequestQueue queue, String seedValue) {
        this.queue = queue;
        this.seedValue = seedValue;
        random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++){
            System.out.println("Client->request "+seedValue);
            queue.putRequest(new Request(seedValue));
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
