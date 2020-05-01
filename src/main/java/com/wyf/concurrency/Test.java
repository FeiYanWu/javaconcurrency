package com.wyf.concurrency;

public class Test {
    private Test(){}
    private  static  class  SingletonHolder {
        private final static Test instance = new Test();
    }
    public static Test getInstance(){
        return SingletonHolder.instance;
    }
    public static void main(String[] args) {
      
    }
}
