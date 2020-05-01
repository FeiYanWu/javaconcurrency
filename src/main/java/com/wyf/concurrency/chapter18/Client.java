package com.wyf.concurrency.chapter18;

public class Client {
    public static void main(String[] args) {
        
        Gate gate = new Gate();
        
        User user = new User("BeiLao","Beijing",gate);

        User user2 = new User("Shanglao","Shanghai",gate);
        User user3 = new User("GUanglao","Guangzhou",gate);

        user.start();
        user2.start();
        user3.start();
    }
}
