package com.wyf.concurrency.chapter31;

public class DisplayStringRequest extends MethodRequest{
    public DisplayStringRequest(Servant servant,final String text) {
        super(servant, null);
        this.text = text;
    }
    private final String text;
    
    

    @Override
    public void execute() {
        this.servant.displayString(text);
    }
}
