package com.wyf.concurrency.chapter9;

public class ProduceConsumeVersion1 {
    
    private int i = 0;
    private final Object LOCK = new Object();
    
    private void produce(){
        synchronized (LOCK){
            System.out.println("P->"+(i++));
        }
    }

    private void consume(){
        synchronized (LOCK){
            System.out.println("C->"+i);
        }
    }

    public static void main(String[] args) {
        ProduceConsumeVersion1 pc = new ProduceConsumeVersion1();
        new Thread("P"){
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
        }.start();
    }
}
