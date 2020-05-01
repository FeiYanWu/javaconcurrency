package com.wyf.guava.utilities;

import sun.text.normalizer.UBiDiProps;

import java.util.concurrent.TimeUnit;

public class ElapsedExample {
    public static void main(String[] args) throws InterruptedException {
        process("3452345");
    }
    private static void process(String orderNo) throws InterruptedException {
        System.out.printf("start process the order %s\n",orderNo);
        long start = System.nanoTime();
        TimeUnit.SECONDS.sleep(1);
        System.out.printf("The orderNo %s process successful and elapsed %d ns.\n",orderNo,(System
            .nanoTime()-start));
    }
}
