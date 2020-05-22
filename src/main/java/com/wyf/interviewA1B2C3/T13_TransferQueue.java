package com.wyf.interviewA1B2C3;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class T13_TransferQueue {

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();
        TransferQueue<Character> queue = new LinkedTransferQueue<>();

        new Thread(()->{
            try {
                for(int i=0;i<aI.length;i++){
                    System.out.print(queue.take());
                    queue.transfer(aI[i]);
                }
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try {
                for(int i=0;i<aC.length;i++){
                    queue.transfer(aC[i]);
                    System.out.print(queue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();
    }
}
