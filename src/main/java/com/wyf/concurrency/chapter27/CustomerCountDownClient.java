package com.wyf.concurrency.chapter27;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class CustomerCountDownClient {
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        CountDown countDown = new CountDown(5);
        IntStream.rangeClosed(1,5).forEach(i->{
            new Thread(String.valueOf(i)){
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+" is running");
                    try {
                        Thread.sleep(RANDOM.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countDown.down();
                }
            }.start();
        });

        countDown.await();
        System.out.println("All works are finished");
        System.out.println("do second thing");
    }
}
