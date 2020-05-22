package com.wyf.disruptor;


import com.lmax.disruptor.EventHandler;
import com.wyf.concurrency.chapter29.CounterTest;

public class LongEventHandler implements EventHandler<LongEvent> {


    private static long count = 0;
    @Override
    public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) throws Exception {
        count++;
        System.out.println("["+Thread.currentThread().getName()+"]"+longEvent+"序号"+sequence);
    }
}
