package com.wyf.concurrency.chapter7;

public class BankVersion2 {
    public static void main(String[] args) {
        final TicketWindowRunnable ticketWindowRunnable = new TicketWindowRunnable();
        Thread windowTicket1 = new Thread(ticketWindowRunnable,"一号窗口");
        Thread windowTicket2 = new Thread(ticketWindowRunnable,"二号窗口");
        Thread windowTicket3 = new Thread(ticketWindowRunnable,"三号窗口");

        windowTicket1.start();
        windowTicket2.start();
        windowTicket3.start();
    }
}
