package com.wyf.concurrency.chapter4;

import javax.swing.text.html.Option;
import java.util.Optional;

public class ThreadSimple {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            Optional.of("Hello").ifPresent(System.out::println);
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }, "t1");
        Optional.of(t.getName()).ifPresent(System.out::println);
        Optional.of(t.getId()).ifPresent(System.out::println);
        Optional.of(t.getPriority()).ifPresent(System.out::println);
        t.start();
    }
}
