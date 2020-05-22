package com.wyf.concurrency;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyContainer<T> {

    final private LinkedList<T> list = new LinkedList<>();

    final private int MAX = 10;

    private int count = 0;

    private Lock lock = new ReentrantLock();
    //Condition 本质  等待队列
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(T t){
        try {
            lock.lock();
            while (list.size() == MAX) {
                producer.await();

            }
            list.add(t);
            ++count;
            consumer.signalAll(); //通知消费者消费
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public synchronized T get(){
        T t = null;
        try{
            lock.lock();
            while (list.size() ==0){
                try {
                    consumer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            t = list.removeFirst();
            count --;
            producer.signalAll();

        }finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        MyContainer<String> c = new MyContainer<>();
        for (int i=0;i<10;i++){
            new Thread(()->{
                for (int j=0;j<5;j++) System.out.println(Thread.currentThread().getName()+" 消费"+c.get());
            },"c"+i).start();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i=0;i<2;i++){
            new Thread(()->{
                for(int j=0;j<25;j++){
                    c.put(Thread.currentThread().getName()+" "+j);
                    System.out.println(Thread.currentThread().getName() + "生产"+Thread.currentThread().getName()+" "+j);
                }
            },"p"+ i).start();
        }
    }
}
