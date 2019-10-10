package com.wyf.concurrency.chapter3;

public class CreateThread3 {
    //在虚拟机栈中压的栈帧
    private static int counter = 0;
    public static void main(String[] args) {
        //create a jvm stack
        try {
            add(0);
        } catch (Error e) {
            e.printStackTrace();
            System.out.println(counter);
        }
    }
    private static void add(int i){
        ++counter;
        add(i+1);
    }
}
//StackOverflowError
//20814
