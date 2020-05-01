package com.wyf.classloader.chapter01;

public class Singleton {


    private static Singleton instance = new Singleton();
    private static int x = 0;
    private static int y;

    private Singleton(){
        x++; 
        y++;
    }

    
    public static void main(String[] args) {
        //Singleton.getInstance();
        System.out.println(Singleton.x);
        System.out.println(Singleton.y);
    }
}
