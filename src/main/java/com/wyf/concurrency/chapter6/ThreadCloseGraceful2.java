package com.wyf.concurrency.chapter6;

public class ThreadCloseGraceful2 {


    public static class Worker extends Thread{
       
        @Override
        public void run() {
//            while (true){
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    break;//return
//                }
//            }
//            //----------------------
//            //----------------------\

             while (true){
                if(Thread.interrupted()){
                    break;
                }
            }
            //----------------------
            //----------------------
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
        worker.interrupt();
    }
}
