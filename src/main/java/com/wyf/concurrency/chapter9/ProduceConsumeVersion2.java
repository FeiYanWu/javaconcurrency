package com.wyf.concurrency.chapter9;

import java.util.stream.Stream;

public class ProduceConsumeVersion2 {
    
    private int i = 0;
    
    private final Object LOCK = new Object();
    
    
    private boolean isProduced = false;
    public void produce(){
        synchronized (LOCK){
            if(isProduced){
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                i++;
                System.out.println("P->"+i);
                LOCK.notify();
                isProduced = true;
            }
            
        }
    }
    
    public void consume(){
        synchronized (LOCK){
            if(isProduced){
                System.out.println("C->"+i);
                LOCK.notify();
                isProduced = false;
            }else{
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    //单p 和但c 没问题
    public static void main(String[] args) {
        ProduceConsumeVersion2 pc = new ProduceConsumeVersion2();
        /*new Thread("P"){
            @Override
            public void run() {
                while (true){
                    pc.produce();
                }
            }
        }.start();

        new Thread("C"){
            @Override
            public void run() {
                while (true){
                    pc.consume();
                }
            }
        }.start();*/

        Stream.of("P1","P2").forEach(n->
            new Thread("P"){
                @Override
                public void run() {
                    while (true){
                        pc.produce();
                    }
                }
            }.start()
        );
        Stream.of("C1","C2").forEach(n->
            new Thread("C"){
                @Override
                public void run() {
                    while (true){
                        pc.consume();
                    }
                }
            }.start()
        );
    }
}
