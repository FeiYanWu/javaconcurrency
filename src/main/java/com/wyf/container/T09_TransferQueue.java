package com.wyf.container;

import java.util.concurrent.LinkedTransferQueue;
//可以给多个线程传递任务  多对对
//要等结果去处理
public class T09_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.transfer("aaa");  //一个线程装完等着  别人取走

        //strs.put("aaa");  //一个线程装完就走


		/*new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();*/


    }
}