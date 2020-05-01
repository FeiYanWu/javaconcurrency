package com.wyf.concurrency.chapter18;

/**
 * single threaded execution design pattern
 * 
 * shared resources
 */
public class Gate {
    
    
    private int counter;
    
    private String name;
    
    private String address;

    /**
     * 临界值
     * 加锁
     * 在读的时候加锁影响效率
     * @param name
     * @param address
     */
    public synchronized void pass(String name,String address){
        this.counter++;
        this.name = name;
        this.address = address;
        verify();
    }

    private void verify() {
        
        if(name.charAt(0) != address.charAt(0)){
            System.out.println("***********BROKEN****************"+toString());
        }
    }

    /**
     * 加锁
     * @return
     */
    public synchronized String toString() {
        return "No."+counter+":"+name+","+address;
    }
}
