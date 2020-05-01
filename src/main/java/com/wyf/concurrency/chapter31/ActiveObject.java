package com.wyf.concurrency.chapter31;

/**
 * 接受异步消息的主动方法
 * 
 * 在Active Object模式中出厂的主动对象可不仅仅有自己特有的线程，它同时还具备可以从外部接收和处理异步消息并根据需要返回处理结果的特征。
 * 
 * 先把方法转换成methodrequest
 */
interface ActiveObject {
    
    Result makeString(int count, char fillChar);
    void displayString(String text);
    
}
