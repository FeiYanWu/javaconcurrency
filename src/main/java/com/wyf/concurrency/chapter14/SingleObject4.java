package com.wyf.concurrency.chapter14;

import java.util.Calendar;

public class SingleObject4 {
    /**
     * double check
     */
    private static SingleObject4 instance;
    private SingleObject4(){}

    //引起空指针异常
    //堆内存开辟一块内存区域
    //里面的一些属性并没有构造完毕，java 编译优化重排序导致
    public static SingleObject4 getInstance(){
        if(null == instance){
            synchronized (SingleObject4.class){
                if(null == instance){
                    instance = new SingleObject4();
                }
            }
        }
        return instance;
    }
}
