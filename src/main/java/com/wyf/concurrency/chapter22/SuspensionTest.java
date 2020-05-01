package com.wyf.concurrency.chapter22;

public class SuspensionTest {
    public static void main(String[] args) throws InterruptedException {
        final RequestQueue queue = new RequestQueue();
        
        new ClientThread(queue,"Alex").start();
        
        ServerThread serverThread = new ServerThread(queue);
        serverThread.start();
        //serverThread.join();  //在wait状态，thread执行没有完成
        
        Thread.sleep(15000);
        serverThread.close();
    }
}
