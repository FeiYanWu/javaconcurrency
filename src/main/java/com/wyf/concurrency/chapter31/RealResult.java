package com.wyf.concurrency.chapter31;

public class RealResult implements Result {
    
    private final Object resultValue;

    public RealResult(Object resultValue) {
        this.resultValue = resultValue;
    }

    @Override
    public Object resultValue() {
        return resultValue;
    }
}
