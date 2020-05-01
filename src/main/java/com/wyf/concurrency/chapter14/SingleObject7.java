package com.wyf.concurrency.chapter14;

import java.util.stream.IntStream;

public class SingleObject7 {
    private SingleObject7(){}
    //枚举类型线程安全
    //构造函数只会被装载一次
    private enum Singleton{
        INSTANCE;
        private final SingleObject7 instance;
        Singleton(){
            instance = new SingleObject7();
        }
        public SingleObject7 getInstance(){
            return instance;
        }
    }
    public static SingleObject7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(1,100).forEach(i->new Thread(String.valueOf(i)){
            @Override
            public void run() {
                System.out.println(SingleObject7.getInstance());
            }
        }.start());
    }
}
