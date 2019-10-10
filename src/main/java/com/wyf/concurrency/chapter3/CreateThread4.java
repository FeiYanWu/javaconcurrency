package com.wyf.concurrency.chapter3;

public class CreateThread4 {
    private static int counter = 0;
    public static void main(String[] args) {
        Thread t1 = new Thread(null,new Runnable() {
            @Override
            public void run() {
                try {
                    add(0);
                } catch (Error e) {
                    e.printStackTrace();
                    System.out.println(counter);
                }
            }
            
            public void add(int i){
                ++counter;
                add(i+1);
            }
        },"Test",1<<24);
        t1.start();
    }
}
