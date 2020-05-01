package com.wyf.concurrency.chapter31;

public class Test {

    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new MakerClient(activeObject,"Alice").start(); 
        new MakerClient(activeObject,"Bobby").start();

        new DisplayThreadClient("Chris",activeObject).start();


    }
}
