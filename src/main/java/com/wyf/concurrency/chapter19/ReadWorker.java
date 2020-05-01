package com.wyf.concurrency.chapter19;


public class ReadWorker extends Thread {

    private final SharedData data;

    public ReadWorker(SharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char[] readBUf = data.read();
                System.out.println(Thread.currentThread().getName()+" read "+ String.valueOf(readBUf));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}




