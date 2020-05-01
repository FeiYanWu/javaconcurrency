package com.wyf.concurrency.chapter6;

public class ThreadCloseGraceful {
    
    public static class Worker extends Thread{
        private volatile boolean start = true;

        @Override
        public void run() {
            while (start){
                System.out.println("Working");
            }
        }
        
        public void shutdown(){
            this.start = false;
            System.out.println("false-------------");

        }
    }
    
    public static void main(String[] args) {
        
        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.shutdown();
        System.out.println("done");
    }   
}
