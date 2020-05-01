package com.wyf.concurrency.chapter31;

public class DisplayThreadClient extends Thread{
    
    private final ActiveObject activeObject;

    public DisplayThreadClient(String name,ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        try {
            for(int i=0;true;i++)
            {
                String text = Thread.currentThread().getName()+"-"+i;
                activeObject.displayString(text);
                Thread.sleep(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
