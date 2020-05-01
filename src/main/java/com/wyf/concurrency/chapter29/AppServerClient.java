package com.wyf.concurrency.chapter29;

import java.io.IOException;

public class AppServerClient {
    public static void main(String[] args) throws InterruptedException, IOException {
        AppServer app = new AppServer();
        app.start();
        
        Thread.sleep(15_000);
        
        app.shutdown();
    }
}
