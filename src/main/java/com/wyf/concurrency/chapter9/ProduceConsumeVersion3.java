package com.wyf.concurrency.chapter9;

import java.util.stream.Stream;

public class ProduceConsumeVersion3 {
    private int i = 0;

    private final Object LOCK = new Object();


    private boolean isProduced = false;

    public void produce() {
        synchronized (LOCK) {
            while (isProduced) {  //if->while  避免重复生产 
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
            System.out.println("P->" + i);
            LOCK.notifyAll();
            isProduced = true;
        }
    }

    public void consume() {
        synchronized (LOCK) {
            while (!isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
           
            System.out.println("C->" + i);
            LOCK.notifyAll();
            isProduced = false;
        }
    }

    //单p 和但c 没问题
    public static void main(String[] args) {
        ProduceConsumeVersion3 pc = new ProduceConsumeVersion3();

        Stream.of("P1", "P2","P3").forEach(n ->
                new Thread(n) {
                    @Override
                    public void run() {
                        while (true) {
                            pc.produce();
                        }
                    }
                }.start()
        );
        Stream.of("C1", "C2","C3","C4").forEach(n ->
                new Thread(n) {
                    @Override
                    public void run() {
                        while (true) {
                            pc.consume();
                        }
                    }
                }.start()
        );
    }
}
