package com.wyf.concurrency.chapter1;

/**************************************
 * @Author : WYF
 * @Date   : 2019/9/2 23:41
 * @Version 1.0
 *************************************/
public class TemplateMethod {
    /**
     * 算法定了，不能被子类复写
     */
    public final void print(String message){
        System.out.println("######################");
        wrapPrint(message);
        System.out.println("######################");

    }

    protected void wrapPrint(String message) {

    }


    public static void main(String[] args) {
        TemplateMethod t1 = new TemplateMethod(){
            @Override
            protected void wrapPrint(String message) {
                System.out.println("*"+message+"*");
            }

        };
        t1.print("Hello Thread");
    }
}
