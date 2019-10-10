package com.wyf.concurrency.chapter3;

public class CreateThread {
    public static void main(String[] args) {
        Thread t = new Thread();
        t.start();
        Thread t2 = new Thread();
        t2.start();
        System.out.println(t.getName());
        System.out.println(t2.getName());
    }
}
