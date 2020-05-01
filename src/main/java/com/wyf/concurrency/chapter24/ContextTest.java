package com.wyf.concurrency.chapter24;

import java.util.stream.IntStream;

public class ContextTest {
    public static void main(String[] args) {
        IntStream.rangeClosed(0,5).forEach(i->{
            new Thread(new ExecutionTask()).start();
        });
    }
}
