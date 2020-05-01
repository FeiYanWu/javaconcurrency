package com.wyf.concurrency.chapter19;

import java.rmi.server.RMIClassLoader;
import java.util.Random;
//写线程
public class WriteWorker extends Thread{
     private static final Random random = new Random(System.currentTimeMillis());
     
     private final SharedData data;
     
     private final String filter;
     
     private int index = 0;

    public WriteWorker(SharedData data, String filter) {
        this.data = data;
        this.filter = filter;
    }

    @Override
    public void run() {
        try {
            while (true){
                char c = nextChar();
                data.write(c);
                Thread.sleep(random.nextInt(1000));
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private char nextChar(){
        char c = filter.charAt(index);
        index++;
        if(index >= filter.length()){
            index = 0;
        }        
        return c;
    }
}
