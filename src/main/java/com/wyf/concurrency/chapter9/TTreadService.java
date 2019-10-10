package com.wyf.concurrency.chapter9;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.Consumer;

public class TTreadService {
    
    final static private LinkedList<Control> CONTROLS = new LinkedList<>();
    private final static int  MAX_WORKS = 5;
    public static void main(String[] args) {
        
        List<Thread> workers = new ArrayList<>();
        Arrays.asList("M1","M2","M3","M4","M5","M6","M7","M8","M9","M10").stream()
                    .map(TTreadService::createCaptureThread)
                    .forEach(t->{
                        t.start();
                        workers.add(t);
                    });
        workers.stream().forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Optional.of("All of capture work finished").ifPresent(System.out::println);
    }
    
    private static Thread createCaptureThread(String name){
        return new Thread(()->{
            Optional.of("The worker "+Thread.currentThread().getName()+" begin capture data").ifPresent(System.out::println);
            synchronized (CONTROLS){
                while (CONTROLS.size()>MAX_WORKS){
                    try {
                        CONTROLS.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                CONTROLS.addLast(new Control());
            }
            Optional.of("The worker ["+Thread.currentThread().getName()+"] is working").ifPresent(System.out::println);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            synchronized (CONTROLS){
                Optional.of("The worker ["+Thread.currentThread().getName()+"] end working").ifPresent(System.out::println);
                CONTROLS.removeFirst();
                CONTROLS.notifyAll();
            }
        },name);
    }
    
    private static class Control{}
}

