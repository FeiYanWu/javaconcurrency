package com.wyf.concurrency.chapter25;

public class BalkingClient {
    public static void main(String[] args) {

        BalkingData balkingData = new BalkingData("D:\\software\\ideaworkspace\\javaconcurrency\\balking.txt", "===BEGIN====");
        new CustomerThread(balkingData).start();
        new WaiterThread(balkingData).start();
    }
}
