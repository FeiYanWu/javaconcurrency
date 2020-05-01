package com.wyf.concurrency.chapter20;

import java.util.stream.IntStream;

public class ImmutableClient {
    public static void main(String[] args) { 
        Person person = new Person("Alex","Datong");
        IntStream.rangeClosed(0,5).forEach(i->{
            new UsePersonThread(person).start();
        });  
        
    }
    
}
