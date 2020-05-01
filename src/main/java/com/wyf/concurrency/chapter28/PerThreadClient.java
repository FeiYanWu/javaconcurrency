package com.wyf.concurrency.chapter28;

import java.util.stream.IntStream;

public class PerThreadClient {
    public static void main(String[] args) {
        final MessageHandler messageHandler = new MessageHandler();

        IntStream.rangeClosed(0,10).forEach(i->messageHandler.request(new Message(String.valueOf(i))));
        
        messageHandler.shutdown();
    }
}
