package com.wyf.concurrency;

/**************************************
 * @Author : WYF
 * @Date   : 2019/9/2 22:56
 * @Version 1.0
 *************************************/
public class TryConCurrency {


    public static void main(String[] args) {
        new Thread("Read-Thread"){
            @Override
            public void run() {
                readFromDataBase();
            }
        }.start();

        new Thread("Write-Thread"){
            @Override
            public void run() {
                writeDataToFile();
            }
        }.start();
    }

    private static void readFromDataBase(){
        System.out.println("Begin read data from db");
        System.out.println("Read Data done and start handle it");
        System.out.println("The Data handle finished and successfully");
    }

    private static void writeDataToFile(){
        System.out.println("Begin write data to file");
        System.out.println("write Data done and start handle it");
        System.out.println("The Data handle finished and successfully");
    }
}
