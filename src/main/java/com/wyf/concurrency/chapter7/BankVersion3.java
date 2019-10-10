package com.wyf.concurrency.chapter7;

public class BankVersion3 {
    public static void main(String[] args) {
        final SynchronizedRunnable ticketWindow = new SynchronizedRunnable();
        Thread windowTicket1 = new Thread(ticketWindow,"一号窗口");
        Thread windowTicket2 = new Thread(ticketWindow,"二号窗口");
        Thread windowTicket3 = new Thread(ticketWindow,"三号窗口");

        windowTicket1.start();
        windowTicket2.start();
        windowTicket3.start();
    }
}
